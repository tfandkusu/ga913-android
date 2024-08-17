package com.tfandkusu.ga913android.ui.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tfandkusu.ga913android.R
import com.tfandkusu.ga913android.analytics.AnalyticsEvent
import com.tfandkusu.ga913android.analytics.AnalyticsEventSender
import com.tfandkusu.ga913android.analytics.AnalyticsEventSenderNoOp
import com.tfandkusu.ga913android.analytics.SendScreenEvent
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
fun LandmarkDetailScreen(
    viewModel: LandmarkDetailViewModel,
    analyticsEventSender: AnalyticsEventSender,
    onBackPressed: () -> Unit,
) {
    val (state, dispatch) = use(viewModel)
    SendScreenEvent(analyticsEventSender, AnalyticsEvent.Screen.LandmarkDetail)
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(
                        state.landmark?.name ?: "",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                hasBack = true,
                onBackPressed = onBackPressed,
            )
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
                item {
                    TitleFavorite(
                        name = it.name,
                        isFavorite = it.isFavorite,
                        onFavoriteClick = {
                            dispatch(Event.OnClickFavorite)
                        },
                    )
                }
                item {
                    ParkState(park = it.park, state = it.state)
                }
                item {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                    )
                }
                item {
                    Text(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = stringResource(id = R.string.landmark_detail_about, it.name),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                item {
                    Text(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
                        text = it.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
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
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(125.dp),
                    )
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(125.dp),
                    )
                    .clip(RoundedCornerShape(125.dp))
                    .size(250.dp),
            model = imageUrl,
            contentDescription = null,
        )
    }
}

@Composable
private fun TitleFavorite(
    name: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorite",
                tint =
                    if (isFavorite) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    },
            )
        }
    }
}

@Composable
private fun ParkState(
    park: String,
    state: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalArrangement = spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = park,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = state,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

class LandmarkDetailViewModelPreview(
    private val previewState: State,
) : LandmarkDetailViewModel {
    override val state: StateFlow<State>
        get() = MutableStateFlow(previewState)
    override val effect: Flow<Effect>
        get() = flow {}

    override fun event(event: Event) {}
}

private class PreviewLandmarkDetailProvider : PreviewParameterProvider<Landmark> {
    override val values: Sequence<Landmark>
        get() =
            sequenceOf(
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
                    id = 1,
                    name = "Turtle Rock",
                    state = "California",
                    isFavorite = true,
                    park = "Joshua Tree National Park",
                    description = "Description",
                    imageUrl = "file:///android_asset/turtlerock.jpg",
                ),
                Landmark(
                    id = 1,
                    name = "AAAAA BBBBB CCCCC DDDDD EEEEE FFFFF GGGGG",
                    state = "California",
                    isFavorite = false,
                    park = "Joshua Tree National Park Park Park Park Park Park",
                    description = "Description",
                    imageUrl = "file:///android_asset/turtlerock.jpg",
                ),
            )
}

@Composable
@Preview
@Preview(
    fontScale = 2.0f,
)
private fun Preview(
    @PreviewParameter(PreviewLandmarkDetailProvider::class) landmark: Landmark,
) {
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
            analyticsEventSender = AnalyticsEventSenderNoOp(),
            onBackPressed = {},
        )
    }
}
