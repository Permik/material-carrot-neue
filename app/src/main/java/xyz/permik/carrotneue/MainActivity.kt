package xyz.permik.carrotneue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import xyz.permik.carrotneue.ui.strings.Strings
import xyz.permik.carrotneue.ui.theme.CarrotNeueTheme

class MainActivity : ComponentActivity() {

    private lateinit var lyricist: Lyricist<Strings>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lyricist = rememberStrings()
            var expanded by remember { mutableStateOf(false) }
            ProvideStrings(lyricist) {
                CarrotNeueTheme {
                    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = { Text(text = LocalStrings.current.helloWorld)},
                                actions = {
                                    IconButton(onClick = { expanded = true },) {
                                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = LocalStrings.current.moreVert)
                                    }
                                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                        DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                                            Text("Refresh")
                                        }
                                        DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                                            Text("Settings")
                                        }
                                    }
                                }
                            )
                                 },
                    ) {
                        Surface(color = MaterialTheme.colors.background) {
                            Greeting("Android")
                        }
                    }

                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarrotNeueTheme {
        Greeting("Android")
    }
}