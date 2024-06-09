package com.android101.backend.core

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spotify.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
