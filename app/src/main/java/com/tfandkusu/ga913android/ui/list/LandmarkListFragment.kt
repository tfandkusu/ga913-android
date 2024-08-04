package com.tfandkusu.ga913android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.compose.content
import com.tfandkusu.ga913android.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandmarkListFragment : Fragment() {
    private val viewModel: LandmarkListViewModelImpl by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.event(LandmarkListViewModel.Event.Load)
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
}
