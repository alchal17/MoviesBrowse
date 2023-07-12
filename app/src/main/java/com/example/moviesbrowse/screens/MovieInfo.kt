package com.example.moviesbrowse.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesbrowse.models.Movie
import com.example.moviesbrowse.ui.theme.DarkGray
import com.example.moviesbrowse.ui.theme.LightGray
import com.example.moviesbrowse.R

@Composable
fun MovieInfo(movie: Movie) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp + 1.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    Box(modifier = Modifier.background(DarkGray)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(screenHeightDp)
                    .width(screenWidthDp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 50.dp, top = 50.dp, end = 50.dp, bottom = 40.dp),
                    contentScale = ContentScale.FillBounds,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 19.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = movie.title,
                    style = TextStyle(color = LightGray, fontSize = 30.sp),
                    fontFamily = FontFamily(
                        Font(R.font.oswald_semi_bold, FontWeight.Normal)
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(bottom = 15.dp, start = 20.dp, end = 13.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                Text(
                    text = movie.overview,
                    style = TextStyle(color = LightGray, fontSize = 15.sp),
                    lineHeight = 23.sp
                )
                Text(
                    text = "Release date: ${movie.release_date}",
                    style = TextStyle(color = LightGray)
                )
                Text(
                    text = "Language: ${movie.original_language}",
                    style = TextStyle(color = LightGray)
                )
            }
        }
    }
}