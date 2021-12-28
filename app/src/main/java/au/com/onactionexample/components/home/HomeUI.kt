package au.com.onactionexample.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class HomeUI {
    @Composable
    fun Home(name: String) {

        var homeModel = HomeModel()
        homeModel.fetchVideos("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos")

        Surface(color = MaterialTheme.colors.primary) {
            Text(text = name)
        }
    }

    @Composable
    fun MyComposable(names: List<String> = listOf("Basics", "Compose")) {
        Column {
            for (name in names) {
                //Greeting(name = name)
            }
        }
    }
}