package com.enigma.enigmamusic.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enigma.enigmamusic.di.Injection
import com.enigma.enigmamusic.model.response.MusicList
import com.enigma.enigmamusic.ui.common.UiState
import com.enigma.enigmamusic.ui.component.CardMusic
import com.enigma.enigmamusic.ui.factory.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepositoru())
    ),
    navigationToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMusic()
            }

            is UiState.Success -> {
                HomeContent(
                    musicList = uiState.data,
                    modifier = modifier,
                    navigationToDetail = navigationToDetail,
                )
            }

            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    musicList: List<MusicList>,
    modifier: Modifier = Modifier,
    navigationToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(musicList) { data ->
            CardMusic(
                image = data.musicList.image,
                song = data.musicList.song,
                artists = data.musicList.artist,
                album = data.musicList.album,
                modifier = Modifier.clickable {
                    navigationToDetail(data.musicList.id)
                }
            )

        }
    }
}

