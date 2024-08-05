package com.tfandkusu.ga913android.ui.list

import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.presentation.MainDispatcherRule
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Event
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.State
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModelImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandmarkDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LandmarkDetailViewModel

    @MockK(relaxed = true)
    private lateinit var repository: LandmarkRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LandmarkDetailViewModelImpl(repository)
    }

    @Test
    fun load() =
        runTest {
            val landmark = mockk<Landmark>()
            coEvery {
                repository.get(2L)
            } returns
                flowOf(
                    landmark,
                )
            viewModel.event(Event.Load(2L))
            assertEquals(
                State(
                    landmark = landmark,
                ),
                viewModel.state.value,
            )
            coVerifySequence {
                repository.get(2L)
            }
        }

    @Test
    fun onClickFavorite() =
        runTest {
            val landmark =
                mockk<Landmark> {
                    every { id } returns 2L
                    every { isFavorite } returns false
                }
            coEvery {
                repository.get(2L)
            } returns
                flowOf(
                    landmark,
                )
            viewModel.event(Event.Load(2L))
            viewModel.event(Event.OnClickFavorite)
            coVerifySequence {
                repository.get(2L)
                repository.favorite(2L, true)
            }
        }
}