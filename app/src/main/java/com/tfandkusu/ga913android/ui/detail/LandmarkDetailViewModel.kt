package com.tfandkusu.ga913android.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.presentation.MyBaseViewModel
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Effect
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Event
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LandmarkDetailViewModel :
    MyBaseViewModel<
        Event,
        State,
        Effect,
    > {
    sealed class Event {
        data class Load(
            val landmarkId: Long,
        ) : Event()

        data object OnClickFavorite : Event()
    }

    data class State(
        val landmark: Landmark? = null,
    )

    sealed class Effect
}

@HiltViewModel
class LandmarkDetailViewModelImpl
    @Inject
    constructor(
        private val repository: LandmarkRepository,
    ) : ViewModel(),
        LandmarkDetailViewModel {
        private val _state = MutableStateFlow(State())
        override val state = _state

        private val channel = createEffectChannel()

        override val effect: Flow<Effect>
            get() = channel.receiveAsFlow()

        override fun event(event: Event) {
            viewModelScope.launch {
                when (event) {
                    is Event.Load -> {
                        repository.get(event.landmarkId).collect { landmark ->
                            _state.value = _state.value.copy(landmark = landmark)
                        }
                    }

                    Event.OnClickFavorite -> {
                        state.value.landmark?.let {
                            repository.favorite(it.id, !it.isFavorite)
                        }
                    }
                }
            }
        }
    }
