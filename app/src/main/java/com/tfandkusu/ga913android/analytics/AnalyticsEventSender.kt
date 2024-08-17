package com.tfandkusu.ga913android.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

interface AnalyticsEventSender {
    fun sendScreen(screen: AnalyticsEvent.Screen)

    fun sendAction(action: AnalyticsEvent.Action)
}

class AnalyticsEventSenderImpl
    @Inject
    constructor(
        private val firebaseAnalytics: FirebaseAnalytics,
    ) : AnalyticsEventSender {
        override fun sendScreen(screen: AnalyticsEvent.Screen) {
            if (screen.isConversionEvent) {
                firebaseAnalytics.logEvent(screen.eventName, null)
            } else {
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screen.eventName)
                firebaseAnalytics.logEvent(
                    FirebaseAnalytics.Event.SCREEN_VIEW,
                    bundle,
                )
            }
        }

        override fun sendAction(action: AnalyticsEvent.Action) {
            val bundle = Bundle()
            action.eventParameters.forEach { (key, value) ->
                when (value) {
                    is String -> {
                        bundle.putString(key, value)
                    }

                    is Int -> {
                        bundle.putInt(key, value)
                    }

                    is Long -> {
                        bundle.putLong(key, value)
                    }

                    is Float -> {
                        bundle.putFloat(key, value)
                    }

                    is Double -> {
                        bundle.putDouble(key, value)
                    }

                    is Boolean -> {
                        bundle.putBoolean(key, value)
                    }
                }
            }
            if (action.isConversionEvent) {
                firebaseAnalytics.logEvent(action.eventName, bundle)
            } else {
                bundle.putString("ga913_action_name", action.eventName)
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
            }
        }
    }
