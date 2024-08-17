package com.tfandkusu.ga913android.analytics

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AnalyticsEventSenderModule {
    @Binds
    @Singleton
    abstract fun bindAnalyticsEventSender(analyticsEventSenderImpl: AnalyticsEventSenderImpl): AnalyticsEventSender
}
