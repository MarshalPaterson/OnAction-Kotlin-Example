package au.com.onactionexample.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import au.com.onaction.OnAction
import au.com.onactionexample.components.home.data.Video

class HomeUI {
    @Composable
    fun Home(name: String) {

        var homeModel = HomeModel()
        homeModel.fetchVideos()

        Surface(color = MaterialTheme.colors.primary) {
            Text(text = name)

            var videos by remember { mutableStateOf(listOf<Video>()) }
            ShowVideos(videos)

            OnAction.addOnAction(Constants.GET_VIDEOS, object : OnAction.OnActionListener {
                override fun onAction(it: Any) {
                    videos = it as List<Video>
                }
            })
        }
    }

    @Composable
    fun ShowVideos(videos: List<Video> = listOf()) {
        Column {
            for (video in videos) {
                ShowVideo(video)
            }
        }
    }

    @Composable
    fun ShowVideo(video: Video) {
        Text(text = "${video.title}!")
    }
}