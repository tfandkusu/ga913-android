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
fun HomeScreen(viewModel: HomeViewModel, analyticsEventSender: AnalyticsEventSender) {
    val (state, effect, dispatch) = use(viewModel)
    SendScreenEvent(analyticsEventSender, AnalyticsEvent.Screen.Home)
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyTheme {
        HomeScreen(analyticsEventSender, viewModel)
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
fun HomeScreen(viewModel: HomeViewModel) {
    val (state, effect, dispatch) = use(viewModel)
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyTheme {
        HomeScreen(viewModel)
    }
}
"""
        val findings = subject.lint(code)
        assertEquals(1, findings.size)
        assertEquals("SendScreenEvent", findings[0].id)
    }
}
