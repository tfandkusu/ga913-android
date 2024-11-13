// https://github.com/tfandkusu/ga913-yaml/ による自動生成コードです。編集しないでください。
package com.tfandkusu.ga913android.analytics

import kotlin.Any
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Map

/**
 * Analytics イベントクラス群
 */
public object AnalyticsEvent {
    /**
     * 画面遷移イベントクラス群
     */
    public sealed class Screen(
        /**
         * Analytics イベント名
         */
        public val eventName: String,
        /**
         * コンバージョンイベントフラグ
         */
        public val isConversionEvent: Boolean,
    ) {
        /**
         * ランドマーク一覧画面
         */
        public data object LandmarkList : Screen("LandmarkList", false)

        /**
         * ランドマーク詳細画面
         */
        public data object LandmarkDetail : Screen("LandmarkDetail", false)

        /**
         * 設定画面
         */
        public data object Setting : Screen("Setting", true)

        /**
         * 情報画面
         */
        public data object Info : Screen("Info", false)

        /**
         * テスト画面
         */
        public data object Test : Screen("Test", false)
    }

    /**
     * 画面内操作イベントクラス群
     */
    public sealed class Action(
        /**
         * Analytics イベント名
         */
        public val eventName: String,
        /**
         * Analytics イベントパラメータ
         */
        public val eventParameters: Map<String, Any>,
        /**
         * コンバージョンイベントフラグ
         */
        public val isConversionEvent: Boolean,
    ) {
        /**
         * ランドマーク一覧画面
         */
        public object LandmarkList {
            /**
             * いいねを付けたランドマークのみを表示するスイッチ
             */
            public data class FavoritesOnlySwitch(
                /**
                 * スイッチの ON/OFF
                 */
                public val favoritesOnly: Boolean,
            ) : Action(
                    "LandmarkListFavoritesOnlySwitch",
                    mapOf(
                        "favorites_only" to favoritesOnly,
                    ),
                    false,
                )
        }

        /**
         * ランドマーク詳細画面
         */
        public object LandmarkDetail {
            /**
             * いいねを付ける
             */
            public data class FavoriteOn(
                /**
                 * ランドマークの ID
                 */
                public val id: Long,
                /**
                 * ランドマークの名前
                 */
                public val name: String,
            ) : Action(
                    "LandmarkDetailFavoriteOn",
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                    true,
                )

            /**
             * いいねを解除する
             */
            public data class FavoriteOff(
                /**
                 * ランドマークの ID
                 */
                public val id: Long,
                /**
                 * ランドマークの名前
                 */
                public val name: String,
            ) : Action(
                    "LandmarkDetailFavoriteOff",
                    mapOf(
                        "id" to id,
                        "name" to name,
                    ),
                    false,
                )
        }

        /**
         * 設定画面
         */
        public object Setting

        /**
         * 情報画面
         */
        public object Info {
            /**
             * プライバシーポリシーを表示する
             */
            public object PrivacyPolicy : Action("InfoPrivacyPolicy", emptyMap(), false)
        }

        /**
         * テスト画面
         */
        public object Test
    }
}
