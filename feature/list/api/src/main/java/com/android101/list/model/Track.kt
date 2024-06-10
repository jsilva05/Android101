package com.android101.list.model

@JvmInline
value class TrackId(val value: String)

data class Track(
    val id: TrackId,
    val name: String,
)
