package com.tfandkusu.ga913android.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfandkusu.ga913android.analytics.AnalyticsEvent
import com.tfandkusu.ga913android.analytics.AnalyticsEventSender
import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.viewmodel.MyBaseViewModel
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Effect
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Event
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LandmarkListViewModel :
    MyBaseViewModel<
        Event,
        State,
        Effect,
        > {
    sealed class Event {
        data object Load : Event()

        data class OnClickLandmark(
            val id: Long,
        ) : Event()

        data class OnChangeFavoritesOnly(
            val value: Boolean,
        ) : Event()
    }

    data class State(
        val landmarks: List<Landmark> = emptyList(),
        val favoritesOnly: Boolean = false,
    )

    sealed class Effect {
        data class NavigateToLandmarkDetail(
            val id: Long,
        ) : Effect()
    }
}

@HiltViewModel
class LandmarkListViewModelImpl
    @Inject
    constructor(
        private val repository: LandmarkRepository,
        private val analyticsEventSender: AnalyticsEventSender,
    ) : ViewModel(),
        LandmarkListViewModel {
        private val _state = MutableStateFlow(State())
        override val state = _state

        private val channel = createEffectChannel()

        override val effect: Flow<Effect>
            get() = channel.receiveAsFlow()

        override fun event(event: Event) {
            viewModelScope.launch {
                when (event) {
                    is Event.Load -> {
                        combine(
                            repository.list(),
                            state.map { it.favoritesOnly },
                        ) { landmarks, favoritesOnly ->
                            if (favoritesOnly) {
                                landmarks.filter { it.isFavorite }
                            } else {
                                landmarks
                            }
                        }.collect {
                            _state.value = _state.value.copy(landmarks = it)
                        }
                    }

                    is Event.OnChangeFavoritesOnly -> {
                        analyticsEventSender.sendAction(
                            AnalyticsEvent.Action.LandmarkList.FavoritesOnlySwitch(
                                favoritesOnly = event.value,
                            ),
                        )
                        _state.value = _state.value.copy(favoritesOnly = event.value)
                    }

                    is Event.OnClickLandmark -> {
                        channel.send(Effect.NavigateToLandmarkDetail(event.id))
                    }
                }
            }
        }
    }
