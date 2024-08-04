package com.tfandkusu.ga913android.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content

class LandmarkListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = content {
        Text("LandmarkListFragment")
    }
}
