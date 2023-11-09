package com.enigma.enigmamusic.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enigma.enigmamusic.R
import com.enigma.enigmamusic.ui.theme.EnigmaMusicTheme
import com.enigma.enigmamusic.ui.theme.Shapes


@Composable
fun CardMusic(
    image: Int,
    song: String,
    artists: String,
    album: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(Shapes.extraLarge)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )

        Spacer(
            modifier = modifier
                .padding(8.dp)
        )

        Text(
            text = song,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )

        Spacer(
            modifier = modifier
                .padding(2.dp)
        )

        Text(
            text = artists,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
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
    }
}

@Preview(showBackground = true)
@Composable
fun CardMusicPreview() {
    EnigmaMusicTheme {
        CardMusic(R.drawable.the_smashing_pumpinks, "1979", "The Smashing Pumpkins", "Melon Collie")
    }
}