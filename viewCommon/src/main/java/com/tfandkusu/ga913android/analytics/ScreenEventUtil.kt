package com.tfandkusu.ga913android.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Fragment が表示された時に画面遷移イベントを送信するための拡張関数
 */
fun Fragment.sendScreenEvent(
    analyticsEventSender: AnalyticsEventSender,
    screen: AnalyticsEvent.Screen,
) {
    this.viewLifecycleOwner.lifecycle.addObserver(
        object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                analyticsEventSender.sendScreen(screen)
            }
        },
    )
}

/**
 * 1画面分の Composable 関数が表示された時に画面遷移イベントを送信するための Composable 関数
 */
@Composable
fun SendScreenEvent(
    analyticsEventSender: AnalyticsEventSender,
    screen: AnalyticsEvent.Screen,
) {
    // 参考にしたコード
    // https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects?hl=ja#7
    val owner = LocalLifecycleOwner.current
    DisposableEffect(owner) {
        val observer =
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    analyticsEventSender.sendScreen(screen)
                }
            }
        owner.lifecycle.addObserver(observer)
        onDispose {
            owner.lifecycle.removeObserver(observer)
        }
    }
}
