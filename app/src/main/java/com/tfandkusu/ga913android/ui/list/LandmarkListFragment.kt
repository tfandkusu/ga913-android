package com.tfandkusu.ga913android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.compose.content
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.tfandkusu.ga913android.theme.MyTheme
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Effect
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandmarkListFragment : Fragment() {
    private val viewModel: LandmarkListViewModelImpl by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.event(Event.Load)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = content {
        MyTheme {
            LandmarkListScreen(viewModel = viewModel)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is Effect.NavigateToLandmarkDetail -> {
                            exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                            findNavController().navigate(
                                LandmarkListFragmentDirections.toLandmarkDetailFragment(
                                    effect.id,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
