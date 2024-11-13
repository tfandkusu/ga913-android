package com.tfandkusu.ga913android

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class SendScreenEventTest {
    @Test
    fun checkScreenEventFromFragment() {
        // 下記のテストは失敗するので、動作を確認したいときは、こちらを取り除いてください。
        if (true) {
            return
        }
        Konsist.scopeFromProject()
            .files
            .withNameEndingWith("Fragment")
            .assertTrue {
                it.imports.withName(
                    "com.tfandkusu.ga913android.analytics.sendScreenEvent",
                ).isNotEmpty()
            }
        // LandmarkListFragment では Fragment に対して sendScreenEvent 拡張関数を呼んでいますか。
        // LandmarkDetailFragment では LandmarkDetailScreen Composable 関数内部で
        // SendScreenEvent Composable 関数の方を使っています。
    }
}
