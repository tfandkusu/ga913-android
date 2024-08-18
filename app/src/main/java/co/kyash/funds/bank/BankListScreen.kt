package co.kyash.funds.bank

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.tfandkusu.ga913android.R
import com.tfandkusu.ga913android.component.MyTopAppBar
import com.tfandkusu.ga913android.presentation.use

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankListScreen(viewModel: BankListViewModel) {
    val (state, dispatch) = use(viewModel)
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.bank_list_title),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                hasBack = true,
            )
        },
    ) { padding ->
    }
}
