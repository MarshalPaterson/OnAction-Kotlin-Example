package au.com.onactionexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import au.com.onaction.OnAction
import au.com.onactionexample.components.home.HomeUI
import au.com.onactionexample.navigation.Navigation
import au.com.onactionexample.ui.theme.OnActionExampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OnActionExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


//                    var homeScreen = HomeUI()
//                    homeScreen.Home(name = "Welcome OnAction with Jetpack Compose")
                    Navigation()

                    var s by remember { mutableStateOf("") }
                    Greeting(s)
                    
                    // Add Listener OnAction to the Action type
                    OnAction.addOnAction(Constants.GET_API, object : OnAction.OnActionListener {
                        override fun onAction(it: Any) {
                            // Do something with 'it' which is the data returning from the action
                            s = it.toString()
                        }
                    })

//                    ButtonDemo()
                }
            }
        }
    }
}

@Composable
fun ButtonDemo() {
    val context = LocalContext.current
    Button(onClick = {
        OnAction.doAction(Constants.GET_API, "NEW TEXT")
    })
    {
        Text("Get API")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello ${name}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnActionExampleTheme {
        Greeting("Android")
    }
}

object Constants {
    const val GET_API = "GET_API"
}