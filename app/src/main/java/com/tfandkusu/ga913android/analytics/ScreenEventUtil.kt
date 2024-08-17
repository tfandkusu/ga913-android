package com.tfandkusu.ga913android.analytics

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

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
