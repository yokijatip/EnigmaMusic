package com.enigma.enigmamusic.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enigma.enigmamusic.R
import com.enigma.enigmamusic.di.Injection
import com.enigma.enigmamusic.ui.common.UiState
import com.enigma.enigmamusic.ui.factory.ViewModelFactory
import com.enigma.enigmamusic.ui.theme.EnigmaMusicTheme
import com.enigma.enigmamusic.ui.theme.Shapes

@Composable
fun DetailScreen(
    musicId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepositoru()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMusicFromId(musicId)
            }

            is UiState.Success -> {
                val data = uiState.data
                data.musicList.lyric?.let {
                    DetailContent(
                        data.musicList.image,
                        data.musicList.song,
                        data.musicList.artist,
                        data.musicList.album,
                        it,
                        onBackClick = navigateBack,
                    )
                }
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    song: String,
    artists: String,
    album: String,
    lyric: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(16.dp)
        ) {

            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .clickable { onBackClick() }
            )

            Spacer(
                modifier = modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            )

            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .clip(Shapes.medium)
                        .height(240.dp)
                        .fillMaxWidth()
                )

            }

            Text(
                text = artists,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF222222)
                ),
                modifier = modifier
                    .padding(vertical = 24.dp)
            )

            Text(
                text = song,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            )

            Text(
                text = album,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Gray

            )

            Text(
                text = "Lyric",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF222222)
                ),
                modifier = modifier
                    .padding(vertical = 16.dp)
            )

            Text(
                text = lyric,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                color = Color.Gray
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    EnigmaMusicTheme {
        DetailContent(
            image = R.drawable.the_smashing_pumpinks,
            song = "1979",
            artists = "The Smashing Pumpkins",
            album = "Melon Collie and The Infinite Sadness",
            lyric = "Senggol dong duda pir awwww",
            onBackClick = { /*TODO*/ })
    }
}