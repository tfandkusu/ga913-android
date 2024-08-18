package com.tfandkusu.detekt

import com.tfandkusu.ga913android.detekt.SendScreenEvent
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Test
import kotlin.test.assertEquals

class SendScreenEventTest {
    private val subject = SendScreenEvent(Config.empty)

    @Test
    fun given_codeWithoutError_then_noReport() {
        val code = """
@Composable
fun LandmarkListScreen(viewModel: LandmarkListViewModel, analyticsEventSender: AnalyticsEventSender) {
    val (state, dispatch) = use(viewModel)
    SendScreenEvent(analyticsEventSender, AnalyticsEvent.Screen.LandmarkList)
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
private fun Preview() {
    MyTheme {
        LandmarkListScreen(analyticsEventSender, viewModel)
    }
}
        """.trimIndent()
        val findings = subject.lint(code)
        assertEquals(0, findings.size)
    }

    @Test
    fun given_codeWithError_then_report() {
        val code = """
@Composable
fun LandmarkListScreen(viewModel: LandmarkListViewModel) {
    val (state, dispatch) = use(viewModel)
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
private fun Preview() {
    MyTheme {
        LandmarkListScreen(viewModel)
    }
}
"""
        val findings = subject.lint(code)
        assertEquals(1, findings.size)
        assertEquals("SendScreenEvent", findings[0].id)
    }
}
