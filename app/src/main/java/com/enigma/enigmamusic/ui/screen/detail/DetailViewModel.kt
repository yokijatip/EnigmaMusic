package com.enigma.enigmamusic.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigma.enigmamusic.model.response.MusicList
import com.enigma.enigmamusic.model.response.Response
import com.enigma.enigmamusic.repository.Repository
import com.enigma.enigmamusic.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<MusicList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MusicList>>
        get() = _uiState

    fun getMusicFromId(musicId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMusicFromId(musicId))
        }
    }

//    fun addToCart(response: Response, count: Int) {
//        viewModelScope.launch {
//            repository.updateOrderReward(response.id, count)
//        }
//    }

}