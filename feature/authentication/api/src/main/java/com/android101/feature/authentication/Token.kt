package com.android101.feature.authentication

import java.time.LocalDateTime

@JvmInline
value class Token(val value: String)

data class AuthenticationToken(
    val token: Token,
    val expiresAt: LocalDateTime,
)
