package com.example.moviesbrowse.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesbrowse.MainActivity
import com.example.moviesbrowse.models.Movie


@Composable
fun MovieCard(movie: Movie, context: MainActivity) {
    val navcontroller = context.navController
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                navcontroller.currentBackStackEntry?.savedStateHandle?.set(
                    key = "movie",
                    value = movie
                )
                navcontroller.navigate("movie_info")
            },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(160.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = "${movie.vote_average.toString().slice(0..2)} / 10",
                    style = TextStyle(color = Color.White, fontSize = 13.sp),
                    modifier = Modifier
                        .padding(start = 5.dp, top = 4.dp)
                        .align(Alignment.TopStart)
                )
            }
            Text(
                text = movie.title,
                style = (TextStyle(fontSize = 12.sp, color = Black)),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 5.dp)
            )
        }
    }
}