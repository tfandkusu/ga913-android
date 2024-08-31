# DroidKaigi 2024「 データに基づく意思決定を支える、Google Analytics for Firebase のイベント送信」の Android アプリの Analytics イベント送信および自動チェック実装例

Apple 公式サンプル [Handling user input](https://developer.apple.com/tutorials/swiftui/handling-user-input) と同じ仕様の Android アプリを自作しました。

それに対して、表題の発表内容に沿って [Google Analytics for Firebase](https://firebase.google.com/docs/analytics) のイベント送信を実装しています。
また画面遷移イベント送信実装抜け自動チェックも実装しています。

# 関連リポジトリ

| リポジトリの役割 | リンク |
| --- | --- |
| YAML ファイルによる Analytics イベント定義 | [tfandkusu/ga913-yaml](https://github.com/tfandkusu/ga913-yaml) |
| 同一仕様の iOS アプリ |[tfandkusu/ga913-ios](https://github.com/tfandkusu/ga913-ios) |
| Kotlin Multiplatform による Analytics イベント定義 | [tfandkusu/ga913-kmp](https://github.com/tfandkusu/ga913-kmp) |

# スクリーンショット

| 一覧画面 | 詳細画面 |
| --- | --- |
| <img src="https://github.com/user-attachments/assets/80772025-30d1-4487-b333-f431424bd4c8" width="320"> | <img src="https://github.com/user-attachments/assets/be857fd4-d063-4816-a1bc-910d3cb0a8dc" width="320"> |

# Analytics イベント送信の実装

Analytics イベント送信は [AnalyticsEventSender クラス](https://github.com/tfandkusu/ga913-android/blob/main/app/src/main/java/com/tfandkusu/ga913android/analytics/AnalyticsEventSender.kt)のメソッドによって行われます。
AnalyticsEvent.Screen, AnalyticsEvent.Action sealed class を実装したインスタンスしか渡せません。それらは [AnalyticsEvent.kt](https://github.com/tfandkusu/ga913-android/blob/main/app/src/main/java/com/tfandkusu/ga913android/analytics/AnalyticsEvent.kt) に定義されていて、[tfandkusu/ga913-yaml](https://github.com/tfandkusu/ga913-yaml) により [iOS アプリ](https://github.com/tfandkusu/ga913-ios)と共通の YAML ファイルから自動生成されます。

それにより、iOS / Android でイベント名やイベントパラメータ名、値の型の違いを仕組みで防いでいます。

# 画面遷移イベント送信実装抜け自動チェック

## detekt カスタムルール

[Compose による画面遷移における Analytics イベント送信](https://github.com/tfandkusu/ga913-android/blob/921353f3651e48a59139d0b3a5ef8642861922ce/app/src/main/java/com/tfandkusu/ga913android/ui/detail/LandmarkDetailScreen.kt#L59)実装抜けを検出する detekt カスタムルールを [detekt-extensions モジュール](https://github.com/tfandkusu/ga913-android/tree/main/detekt-extensions)に構築しています。

こちらを参考に構築しました。

- [Detekt for Android— How To Make Your Own Custom Rule](https://medium.com/@emrekoc/detekt-for-android-how-to-make-your-own-custom-rule-2861eb60e4ba)

## Konsist

[Fragment による画面遷移における Analytics イベント送信](https://github.com/tfandkusu/ga913-android/blob/921353f3651e48a59139d0b3a5ef8642861922ce/app/src/main/java/com/tfandkusu/ga913android/ui/list/LandmarkListFragment.kt#L52)実装抜けを検出する [Konsist](https://github.com/LemonAppDev/konsist/) を用いた単体テストを[こちら](https://github.com/tfandkusu/ga913-android/blob/main/app/src/test/java/com/tfandkusu/ga913android/ui/SendScreenEventTest.kt)に実装しています。
