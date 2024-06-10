package com.android101.core.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MoshiModule {
    @Provides
    @Singleton
    fun provideMoshi(
        factories: Set<@JvmSuppressWildcards JsonAdapter.Factory>,
    ): Moshi =
        Moshi.Builder()
            .apply { factories.forEach { add(it) } }
            .build()
}
