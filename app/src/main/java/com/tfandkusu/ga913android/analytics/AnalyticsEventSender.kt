package com.tfandkusu.ga913android.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.tfandkusu.ga913kmp.analytics.KmpAnalyticsEventAction
import com.tfandkusu.ga913kmp.analytics.KmpAnalyticsEventScreen
import javax.inject.Inject

/**
 * Analytics イベントを送信する担当インターフェース
 */
interface AnalyticsEventSender {
    /**
     * 画面遷移イベントを送信する
     *
     * @param screen 画面遷移イベント
     */
    fun sendScreen(screen: AnalyticsEvent.Screen)

    /**
     * 画面内操作イベントを送信する
     *
     * @param action 画面内操作イベント
     */
    fun sendAction(action: AnalyticsEvent.Action)

    /**
     * KMP 版のイベントクラスを使用して、画面遷移イベントを送信する。
     *
     * @param screen 画面遷移イベント
     */
    fun sendScreen(screen: KmpAnalyticsEventScreen)

    /**
     * KMP 版のイベントクラスを使用して、画面内操作イベントを送信する。
     *
     * @param action 画面内操作イベント
     */
    fun sendAction(action: KmpAnalyticsEventAction)
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

        override fun sendScreen(screen: KmpAnalyticsEventScreen) {
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

        override fun sendAction(action: KmpAnalyticsEventAction) {
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

class AnalyticsEventSenderNoOp : AnalyticsEventSender {
    override fun sendScreen(screen: AnalyticsEvent.Screen) {
        // no-op
    }

    override fun sendAction(action: AnalyticsEvent.Action) {
        // no-op
    }

    override fun sendScreen(screen: KmpAnalyticsEventScreen) {
        // no-op
    }

    override fun sendAction(action: KmpAnalyticsEventAction) {
        // no-op
    }
}
