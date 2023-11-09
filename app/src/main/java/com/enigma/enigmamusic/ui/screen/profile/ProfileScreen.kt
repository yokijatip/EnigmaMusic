package com.enigma.enigmamusic.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.enigma.enigmamusic.R
import com.enigma.enigmamusic.ui.theme.EnigmaMusicTheme
import com.enigma.enigmamusic.ui.theme.Shapes

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Profile()
    }

}

@Composable
fun Profile(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp, vertical = 64.dp)
        ) {
            Box(
                modifier = modifier,

                ) {
                Image(
                    painter = painterResource(R.drawable.landscape),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .clip(Shapes.medium)
                        .height(240.dp)
                        .fillMaxWidth()
                )

                Column(
                    modifier = modifier
                        .padding(8.dp)
                        .align(Center)
                ) {
                    Image(
                        painter = painterResource(R.drawable.yoki),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .padding(vertical = 24.dp)
                            .align(CenterHorizontally)
                            .clip(CircleShape)
                            .height(82.dp)
                            .width(82.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Yoki Jati Perkasa",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFFFFFFF)
                        ),
                        modifier = modifier
                            .padding(vertical = 4.dp)
                    )

                    Text(
                        text = "yokijati@gmail.com",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        color = Color.LightGray,
                        modifier = modifier
                            .align(CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PofilePreview() {
    EnigmaMusicTheme {
        ProfileScreen()
    }
}