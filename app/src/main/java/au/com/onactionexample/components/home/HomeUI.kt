package au.com.onactionexample.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import au.com.onaction.OnAction
import au.com.onactionexample.components.home.data.Video
import au.com.onactionexample.navigation.Screen

//class HomeUI {
    @Composable
    fun HomeUI(navController: NavController) {

        var homeModel = HomeModel()
        homeModel.fetchVideos()

        Surface(color = MaterialTheme.colors.primary) {
            Text(text = "Home")
            Button(
                onClick = {
                    navController.navigate(Screen.DetailUI.args("text"))
                },
            ) {
                Text(text = "GO")
            }

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
        Text(text = "${video.speaker}!")
    }
//}