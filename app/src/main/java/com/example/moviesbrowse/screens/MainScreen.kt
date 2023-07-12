package com.example.moviesbrowse.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesbrowse.MainActivity
import com.example.moviesbrowse.elements.MovieCard
import com.example.moviesbrowse.elements.TopBar
import com.example.moviesbrowse.ui.theme.DarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(context: MainActivity) {
    val viewModel = context.viewModel
    val lazyGridState = rememberLazyGridState()
    if (lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == viewModel.currentMovies.size - 1) {
        viewModel.addPage()
    }
    Scaffold(
        topBar = { TopBar() },
        containerColor = DarkGray
    ) { content_padding ->
        Box(
            modifier = Modifier.padding(
                bottom = content_padding.calculateBottomPadding(),
                top = content_padding.calculateTopPadding() + 5.dp
            )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                state = lazyGridState
            ) {
                items(viewModel.currentMovies) { movie -> MovieCard(movie, context) }
            }
        }
    }
}