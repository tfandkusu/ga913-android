package com.tfandkusu.ga913android.ui.list

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.tfandkusu.ga913android.R
import com.tfandkusu.ga913android.component.MyTopAppBar
import com.tfandkusu.ga913android.model.Landmark
import com.tfandkusu.ga913android.theme.MyTheme
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Effect
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.Event
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel.State
import com.tfandkusu.ga913android.viewmodel.use
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkListScreen(viewModel: LandmarkListViewModel) {
    val (state, dispatch) = use(viewModel)
    Scaffold(
        topBar = {
            MyTopAppBar(title = { Text(stringResource(R.string.app_name)) })
        },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentPadding = padding,
        ) {
            item {
                FavoritesOnlySwitch(
                    favoritesOnly = state.favoritesOnly,
                    onCheckedChange = { favoritesOnly ->
                        dispatch(Event.OnChangeFavoritesOnly(favoritesOnly))
                    },
                )
            }
            items(
                items = state.landmarks,
                key = { landmark ->
                    landmark.id
                },
            ) { landmark ->
                LandmarkListItem(
                    modifier = Modifier.animateItem(),
                    landmark = landmark,
                    onClick = {
                        dispatch(Event.OnClickLandmark(landmark.id))
                    },
                )
            }
        }
    }
}

@Composable
private fun FavoritesOnlySwitch(
    favoritesOnly: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalArrangement = spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.landmark_list_favorites_only),
                style = MaterialTheme.typography.bodyLarge,
            )
            Switch(checked = favoritesOnly, onCheckedChange = onCheckedChange)
        }
        HorizontalDivider()
    }
}

private class PreviewLandmarkListProvider : PreviewParameterProvider<List<Landmark>> {
    override val values: Sequence<List<Landmark>>
        get() =
            sequenceOf(
                listOf(
                    Landmark(
                        id = 1,
                        name = "Turtle Rock",
                        state = "California",
                        isFavorite = false,
                        park = "Joshua Tree National Park",
                        description = "Description",
                        imageUrl = "file:///android_asset/turtlerock.jpg",
                    ),
                    Landmark(
                        id = 2,
                        name = "Silver Salmon Creek",
                        state = "Alaska",
                        isFavorite = true,
                        park = "Lake Clark National Park and Preserve",
                        description = "Description",
                        imageUrl = "file:///android_asset/silversalmoncreek.jpg",
                    ),
                    Landmark(
                        id = 3,
                        name = "Chilkoot Trail",
                        state = "Alaska",
                        isFavorite = false,
                        park = "Klondike Gold Rush National Historical Park",
                        description = "Description",
                        imageUrl = "file:///android_asset/chilkoottrail.jpg",
                    ),
                ),
            )
}

class LandmarkListViewModelPreview(
    private val previewState: State,
) : LandmarkListViewModel {
    override val state: StateFlow<State>
        get() = MutableStateFlow(previewState)
    override val effect: Flow<Effect>
        get() = flow {}

    override fun event(event: Event) {}
}

@Composable
@Preview
@Preview(
    fontScale = 2.0f,
)
private fun Preview(
    @PreviewParameter(PreviewLandmarkListProvider::class) landmarks: List<Landmark>,
) {
    val state =
        State(
            landmarks = landmarks,
            favoritesOnly = false,
        )
    MyTheme {
        LandmarkListScreen(
            viewModel =
                LandmarkListViewModelPreview(
                    state,
                ),
        )
    }
}
