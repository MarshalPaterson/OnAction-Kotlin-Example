@file:OptIn(ExperimentalEncodingApi::class)

package au.com.onactionexample.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import au.com.onaction.OnAction
import au.com.onactionexample.components.common.data.Video
import au.com.onactionexample.navigation.Screen
import kotlin.io.encoding.ExperimentalEncodingApi


@Composable
fun HomeUi(navController: NavController) {

    var homeModel = HomeModel()
    homeModel.fetchVideos()

    Surface() {
        var videos by remember { mutableStateOf(listOf<Video>()) }
        ShowVideos(videos, navController)

        // OnAction listens for any event being sent from the Model
        OnAction.addOnAction(Constants.GET_VIDEOS, object : OnAction.OnActionListener {
            override fun onAction(it: Any) {
                videos = it as List<Video>
            }
        })
    }
}

@Composable
fun ShowVideos(videos: List<Video> = listOf(), navController: NavController) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        for (video in videos) {
            ShowVideo(video, navController)
        }
    }
}

@Composable
fun ShowVideo(video: Video, navController: NavController) {
    Text(text = "${video.title}!")
    Text(text = "${video.speaker}!")

    val url = video.videoUrl.split("=").toTypedArray()

    Button(
        onClick = {
            navController.navigate(Screen.DetailUi.args("${url.get(1)}"))
        },
    ) {
        Text(text = "Watch Video")
    }

}
