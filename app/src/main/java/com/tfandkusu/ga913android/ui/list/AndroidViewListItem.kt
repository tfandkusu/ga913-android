package com.tfandkusu.ga913android.ui.list

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.tfandkusu.ga913android.theme.MyTheme

@Composable
fun AndroidViewListItem() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        AndroidView(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            factory = { context ->
                Log.d("GA913LOG", "AndroidView factory")
                val textView = TextView(context)
                val dp = context.resources.displayMetrics.density
                textView.setPadding((16 * dp).toInt(), (16 * dp).toInt(), (16 * dp).toInt(), (16 * dp).toInt())
                textView.text = "AndroidView"
                textView
            },
            onReset = { view ->
                view
            },
            onRelease = {
                Log.d("GA913LOG", "AndroidView onRelease")
            },
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    MyTheme {
        AndroidViewListItem()
    }
}
