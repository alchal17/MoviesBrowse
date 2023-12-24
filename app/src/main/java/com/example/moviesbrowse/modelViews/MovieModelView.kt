package com.example.moviesbrowse.modelViews

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviesbrowse.API_KEY
import com.example.moviesbrowse.models.Movie
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CompletableFuture

class MovieModelView : ViewModel() {
    var page by mutableStateOf(1)
        private set


    var currentMovies: MutableList<Movie> by mutableStateOf(mutableListOf())

    suspend fun addPage() {
        this.currentMovies.addAll(getMovies(page + 1))
        this.page++
    }

    suspend fun getMovies(page: Int = 1): List<Movie> {
        val url = "https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY&page=$page"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            val jsonObject = JSONObject(json)
            val jsonArrayStr = jsonObject.getJSONArray("results").toString()
            val gson = Gson()
            val movies = gson.fromJson(jsonArrayStr, Array<Movie>::class.java)
            movies.toList()
        }
    }
}