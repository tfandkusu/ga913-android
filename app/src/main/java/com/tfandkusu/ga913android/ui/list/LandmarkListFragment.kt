package com.tfandkusu.ga913android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.tfandkusu.ga913android.theme.MyTheme

class LandmarkListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = content {
        MyTheme {
            LandmarkListScreen()
        }
    }
}
