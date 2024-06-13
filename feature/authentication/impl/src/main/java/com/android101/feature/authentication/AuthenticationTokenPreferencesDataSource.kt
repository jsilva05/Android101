package com.android101.feature.authentication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import javax.inject.Inject

internal interface AuthenticationTokenPreferencesDataSource {
    fun getToken(): Flow<AuthenticationToken?>
    suspend fun setToken(token: String, expiresIn: Long)
}

internal class AuthenticationTokenPreferencesDataSourceImpl @Inject constructor(
    @AuthenticationTokenDataStore private val dataStore: DataStore<Preferences>,
    private val clock: Clock,
    private val zoneOffset: ZoneOffset,
) : AuthenticationTokenPreferencesDataSource {
    override fun getToken(): Flow<AuthenticationToken?> =
        dataStore.data.map { preferences ->
            val token = preferences[authTokenKey] ?: return@map null
            val expiresAt = preferences[authTokenExpiresAt] ?: return@map null

            AuthenticationToken(
                token = Token(token),
                expiresAt = LocalDateTime.ofEpochSecond(expiresAt, 0, zoneOffset),
            )
        }

    override suspend fun setToken(token: String, expiresIn: Long) {
        val expiresAt = LocalDateTime.now(clock).plus(expiresIn, ChronoUnit.SECONDS)
        dataStore.edit { preferences ->
            preferences[authTokenKey] = token
            preferences[authTokenExpiresAt] = expiresAt.toEpochSecond(zoneOffset)
        }
    }

    companion object {
        private val authTokenKey = stringPreferencesKey("authenticationToken")
        private val authTokenExpiresAt = longPreferencesKey("authenticationTokenExpiry")
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthenticationTokenPreferencesDataSourceModule {
    @Binds
    abstract fun bind(impl: AuthenticationTokenPreferencesDataSourceImpl): AuthenticationTokenPreferencesDataSource
}
