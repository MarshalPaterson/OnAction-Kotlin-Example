package au.com.onactionexample.components.home

import au.com.onactionexample.components.home.data.Videos
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException


class HomeModel {
    private val client = OkHttpClient()

    fun fetchVideos(url: String) = runBlocking {
        launch {
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    val gson = GsonBuilder().create()
                    val videos = gson.fromJson(response.body()?.string(), Videos::class.java)
                    println(videos)
                }
            })
        }
    }

//    suspend fun fetchVideo(id: Int): Video {
//        val response = window
//            .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
//            .await()
//            .text()
//            .await()
//        return Json.decodeFromString(response)
//    }
    companion object Services {
        private const val videosUrl = "https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos"
    }
}