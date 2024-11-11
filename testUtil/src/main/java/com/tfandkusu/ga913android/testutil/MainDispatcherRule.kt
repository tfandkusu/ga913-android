package com.tfandkusu.ga913android.testutil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule
    @OptIn(ExperimentalCoroutinesApi::class)
    constructor(
        val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
    ) : org.junit.rules.TestWatcher() {
        override fun starting(description: org.junit.runner.Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: org.junit.runner.Description) {
            Dispatchers.resetMain()
        }
    }
