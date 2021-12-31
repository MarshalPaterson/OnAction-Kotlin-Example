package au.com.onactionexample.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import au.com.onaction.OnAction
import au.com.onactionexample.components.common.data.Video
import au.com.onactionexample.navigation.Screen


@Composable
fun HomeUi(navController: NavController) {

    var homeModel = HomeModel()
    homeModel.fetchVideos()

    Surface() {
        Text(text = "Home")

        var videos by remember { mutableStateOf(listOf<Video>()) }
        ShowVideos(videos, navController)

        OnAction.addOnAction(Constants.GET_VIDEOS, object : OnAction.OnActionListener {
            override fun onAction(it: Any) {
                videos = it as List<Video>
            }
        })
    }
}

@Composable
fun ShowVideos(videos: List<Video> = listOf(), navController: NavController) {
    Column {
        for (video in videos) {
            ShowVideo(video, navController)
        }
    }
}

@Composable
fun ShowVideo(video: Video, navController: NavController) {
    Text(text = "${video.title}!")
    Text(text = "${video.speaker}!")

    Button(
        onClick = {
            navController.navigate(Screen.DetailUi.args("text"))
        },
    ) {
        Text(text = "GO")
    }

}
