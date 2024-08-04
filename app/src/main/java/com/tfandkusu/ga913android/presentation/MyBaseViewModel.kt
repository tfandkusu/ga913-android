package com.tfandkusu.ga913android.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MyBaseViewModel<Event, State, Effect> {
    val state: StateFlow<State>
    val effect: Flow<Effect>
    fun event(event: Event)
    fun createEffectChannel(): Channel<Effect> {
        return Channel(Channel.UNLIMITED)
    }
}
