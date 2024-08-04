package com.tfandkusu.ga913android.ui.list

import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.presentation.MainDispatcherRule
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Event
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.State
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandmarkListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LandmarkListViewModel

    @MockK
    private lateinit var repository: LandmarkRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LandmarkListViewModelImpl(repository)
    }

    @Test
    fun load() =
        runTest {
            val landmarks =
                listOf(
                    mockk<Landmark>(),
                    mockk<Landmark>(),
                )
            coEvery {
                repository.list()
            } returns
                flowOf(
                    landmarks,
                )
            viewModel.event(Event.Load)
            assertEquals(
                State(
                    landmarks = landmarks,
                ),
                viewModel.state.value,
            )
        }
}
