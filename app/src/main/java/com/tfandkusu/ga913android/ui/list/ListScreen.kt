package com.tfandkusu.ga913android.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tfandkusu.ga913android.R
import com.tfandkusu.ga913android.component.MyTopAppBar
import com.tfandkusu.ga913android.theme.MyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkListScreen() {
    Scaffold(
        topBar = {
            MyTopAppBar(title = { Text(stringResource(R.string.app_name)) })
        },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
        }
    }
}

@Composable
@Preview
fun LandmarkListScreenPreview() {
    MyTheme {
        LandmarkListScreen()
    }
}
