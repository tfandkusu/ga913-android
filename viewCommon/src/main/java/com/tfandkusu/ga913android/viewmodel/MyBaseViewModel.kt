package com.tfandkusu.ga913android.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MyBaseViewModel<Event, State, Effect> {
    val state: StateFlow<State>
    val effect: Flow<Effect>

    fun event(event: Event)

    fun createEffectChannel(): Channel<Effect> = Channel(Channel.UNLIMITED)
}

data class StateDispatch<Event, State>(
    val state: State,
    val dispatch: (Event) -> Unit,
)

@Suppress("MaxLineLength")
@Composable
inline fun <reified Event, State, Effect> use(viewModel: MyBaseViewModel<Event, State, Effect>): StateDispatch<Event, State> {
    val state: State by viewModel.state.collectAsState()
    val dispatch =
        remember(viewModel) {
            { event: Event ->
                viewModel.event(event)
            }
        }
    return StateDispatch(
        state = state,
        dispatch = dispatch,
    )
}
