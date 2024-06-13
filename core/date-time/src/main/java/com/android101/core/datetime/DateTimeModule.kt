package com.android101.core.datetime

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import java.time.ZoneOffset

@Module
@InstallIn(SingletonComponent::class)
internal object DateTimeModule {
    @Provides
    fun provideClock(): Clock = Clock.systemDefaultZone()

    @Provides
    fun provideZoneOffset(): ZoneOffset = ZoneOffset.UTC
}
