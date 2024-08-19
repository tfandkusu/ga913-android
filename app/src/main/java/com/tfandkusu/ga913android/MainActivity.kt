package com.tfandkusu.ga913android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tfandkusu.ga913android.analytics.AnalyticsEventSender
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var analyticsEventSender: AnalyticsEventSender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        when (NAVIGATION_MODE) {
            NavigationMode.FRAGMENT -> {
                setContentView(R.layout.main)
            }
            NavigationMode.COMPOSE -> {
                setContent {
                    MainNavHost(analyticsEventSender = analyticsEventSender)
                }
            }
        }
    }

    enum class NavigationMode {
        FRAGMENT,
        COMPOSE,
    }

    companion object {
        val NAVIGATION_MODE = NavigationMode.COMPOSE
    }
}
