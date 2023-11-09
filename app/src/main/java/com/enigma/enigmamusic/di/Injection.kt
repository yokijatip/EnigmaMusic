package com.enigma.enigmamusic.di

import com.enigma.enigmamusic.repository.Repository

object Injection {
    fun provideRepositoru(): Repository {
        return Repository.getInstance()
    }
}