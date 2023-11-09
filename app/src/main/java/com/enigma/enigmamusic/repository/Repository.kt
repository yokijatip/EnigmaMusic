package com.enigma.enigmamusic.repository

import com.enigma.enigmamusic.model.local.MusicDataSource
import com.enigma.enigmamusic.model.response.MusicList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {

    private val musicList = mutableListOf<MusicList>()

    init {
        if (musicList.isEmpty()) {
            MusicDataSource.songList.forEach {
                musicList.add(MusicList(it))
            }
        }
    }

    fun getAllMusic(): Flow<List<MusicList>> {
        return flowOf(musicList)
    }

    fun getMusicFromId(musicId: Long): MusicList {
        return musicList.first {
            it.musicList.id == musicId
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }

}