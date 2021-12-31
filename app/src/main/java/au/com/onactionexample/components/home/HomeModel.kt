package au.com.onactionexample.components.home

import au.com.onaction.OnAction
import au.com.onactionexample.components.home.data.Videos
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import java.io.IOException


class HomeModel {
    private val client = OkHttpClient()

    fun fetchVideos() = runBlocking {
        launch {
            val request = Request.Builder()
                .url(Services.VIDEOS_URL)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    val gson = GsonBuilder().create()
                    val videos = gson.fromJson(response.body?.string(), Videos::class.java)
                    OnAction.doAction(Constants.GET_VIDEOS, videos)
                }
            })
        }
    }
}

object Services {
    const val VIDEOS_URL = "https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos"
}

object Constants {
    const val GET_VIDEOS = "GET_VIDEOS"
}