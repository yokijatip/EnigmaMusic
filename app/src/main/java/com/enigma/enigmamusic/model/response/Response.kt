package com.enigma.enigmamusic.model.response

data class Response(
    val id: Long,
    val image: Int,
    val song: String,
    val artist: String,
    val album: String,
    val lyric: String? = null
)
