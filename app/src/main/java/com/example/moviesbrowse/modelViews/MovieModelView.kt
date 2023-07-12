package com.example.moviesbrowse.modelViews

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviesbrowse.API_KEY
import com.example.moviesbrowse.models.Movie
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CompletableFuture

class MovieModelView : ViewModel() {
    var page by mutableStateOf(1)
        private set


    var currentMovies: MutableList<Movie> by mutableStateOf(getMovies(page).get().toMutableList())
        private set

    fun addPage() {
        this.currentMovies.addAll(getMovies(page + 1).get())
        this.page++
    }

    private fun getMovies(page: Int = 1): CompletableFuture<Array<Movie>> {
        val url = "https://api.themoviedb.org/3/trending/movie/day?api_key=$API_KEY&page=$page"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val future = CompletableFuture<Array<Movie>>()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                future.completeExceptionally(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    val jsonObject = JSONObject(responseBody)
                    val jsonArrayStr = jsonObject.getJSONArray("results").toString()
                    val movies = Gson().fromJson(jsonArrayStr, Array<Movie>::class.java)
                    future.complete(movies)
                } else {
                    future.complete(emptyArray())
                }
            }
        })
        return future
    }
}