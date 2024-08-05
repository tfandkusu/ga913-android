package com.tfandkusu.ga913android.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.compose.content
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialSharedAxis
import com.tfandkusu.ga913android.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandmarkDetailFragment : Fragment() {
    private val viewModel: LandmarkDetailViewModelImpl by viewModels()

    private val args: LandmarkDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        viewModel.event(
            LandmarkDetailViewModel.Event.Load(landmarkId = args.landmarkId),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = content {
        MyTheme {
            LandmarkDetailScreen(viewModel = viewModel)
        }
    }
}
