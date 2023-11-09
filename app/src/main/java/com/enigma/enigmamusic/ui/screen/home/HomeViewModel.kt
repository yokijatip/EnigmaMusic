package com.enigma.enigmamusic.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigma.enigmamusic.model.response.MusicList
import com.enigma.enigmamusic.repository.Repository
import com.enigma.enigmamusic.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<MusicList>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<MusicList>>>
        get() = _uiState

    fun getAllMusic() {
        viewModelScope.launch {
            repository.getAllMusic()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { musicList ->
                    _uiState.value = UiState.Success(musicList)
                }
        }
    }

}