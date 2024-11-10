package com.tfandkusu.ga913android.landmark.list

import com.tfandkusu.ga913android.analytics.AnalyticsEvent
import com.tfandkusu.ga913android.analytics.AnalyticsEventSender
import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.landmark.MainDispatcherRule
import com.tfandkusu.ga913android.landmark.list.LandmarkListViewModel.Effect
import com.tfandkusu.ga913android.landmark.list.LandmarkListViewModel.Event
import com.tfandkusu.ga913android.landmark.list.LandmarkListViewModel.State
import com.tfandkusu.ga913android.model.Landmark
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.first
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

    @MockK(relaxed = true)
    private lateinit var analyticsEventSender: AnalyticsEventSender

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LandmarkListViewModelImpl(repository, analyticsEventSender)
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
            coVerifySequence {
                repository.list()
            }
        }

    @Test
    fun onClickLandmark() =
        runTest {
            viewModel.event(Event.OnClickLandmark(id = 2))
            assertEquals(Effect.NavigateToLandmarkDetail(id = 2), viewModel.effect.first())
        }

    @Test
    fun onChangeFavoritesOnly() =
        runTest {
            val landmarks =
                listOf(
                    mockk<Landmark> {
                        every { id } returns 1
                        every { isFavorite } returns false
                    },
                    mockk<Landmark> {
                        every { id } returns 2
                        every { isFavorite } returns true
                    },
                    mockk<Landmark> {
                        every { id } returns 3
                        every { isFavorite } returns false
                    },
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
                    favoritesOnly = false,
                ),
                viewModel.state.value,
            )
            viewModel.event(Event.OnChangeFavoritesOnly(value = true))
            assertEquals(
                State(
                    landmarks =
                        listOf(
                            landmarks[1],
                        ),
                    favoritesOnly = true,
                ),
                viewModel.state.value,
            )
            coVerifySequence {
                analyticsEventSender.sendAction(
                    AnalyticsEvent.Action.LandmarkList.FavoritesOnlySwitch(
                        favoritesOnly = true,
                    ),
                )
            }
        }
}
