package com.example.moviesbrowse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesbrowse.modelViews.MovieModelView
import com.example.moviesbrowse.models.Movie
import com.example.moviesbrowse.screens.MainScreen
import com.example.moviesbrowse.screens.MovieInfo


class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MovieModelView>()
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main_page") {
                composable("main_page") { MainScreen(context = this@MainActivity) }
                composable("movie_info") {
                    val movie =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")
                    movie?.let { item -> MovieInfo(movie = item) }
                }
            }
        }
    }
}

