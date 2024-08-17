package com.tfandkusu.ga913android.data.di

import android.content.Context
import com.tfandkusu.ga913android.data.LandmarkRepository
import com.tfandkusu.ga913android.data.LandmarkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideLandmarkRepository(
        @ApplicationContext context: Context,
    ): LandmarkRepository = LandmarkRepositoryImpl(context = context)
}
