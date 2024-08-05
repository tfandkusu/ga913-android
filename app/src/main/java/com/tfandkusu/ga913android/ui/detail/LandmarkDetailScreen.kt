package com.tfandkusu.ga913android.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tfandkusu.ga913android.component.MyTopAppBar
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.presentation.use
import com.tfandkusu.ga913android.theme.MyTheme
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Effect
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Event
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkDetailScreen(viewModel: LandmarkDetailViewModel) {
    val (state, dispatch) = use(viewModel)
    Scaffold(
        topBar = {
            MyTopAppBar(title = { Text(state.landmark?.name ?: "") })
        },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentPadding = padding,
        ) {
            state.landmark?.let {
                item {
                    Image(it.imageUrl)
                }
            }
        }
    }
}

@Composable
private fun Image(imageUrl: String) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(125.dp))
                    .size(250.dp),
            model = imageUrl,
            contentDescription = null,
        )
    }
}

class LandmarkDetailViewModelPreview(
    private val previewState: LandmarkDetailViewModel.State,
) : LandmarkDetailViewModel {
    override val state: StateFlow<State>
        get() = MutableStateFlow(previewState)
    override val effect: Flow<Effect>
        get() = flow {}

    override fun event(event: Event) {}
}

@Composable
@Preview
private fun Preview() {
    val landmark =
        Landmark(
            id = 1,
            name = "Turtle Rock",
            state = "California",
            isFavorite = false,
            park = "Joshua Tree National Park",
            description = "Description",
            imageUrl = "file:///android_asset/turtlerock.jpg",
        )
    val state =
        State(
            landmark = landmark,
        )
    MyTheme {
        LandmarkDetailScreen(
            viewModel =
                LandmarkDetailViewModelPreview(
                    state,
                ),
        )
    }
}
