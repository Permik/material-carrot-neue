package xyz.permik.carrotneue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import kotlinx.coroutines.launch
import xyz.permik.carrotneue.ui.strings.Strings
import xyz.permik.carrotneue.ui.theme.CarrotNeueTheme

class MainActivity : ComponentActivity() {

    private lateinit var lyricist: Lyricist<Strings>


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lyricist = rememberStrings()
            var expandedAppBar by remember { mutableStateOf(false) }
            var selectedProfile by remember { mutableStateOf("Test") }
            ProvideStrings(lyricist) {
                CarrotNeueTheme {
                    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = { Text(text = LocalStrings.current.appName)},
                                actions = {
                                    DropdownMenu(expanded = expandedAppBar, onDismissRequest = { expandedAppBar = false }) {
                                        DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                                            Text("Refresh")
                                        }
                                        DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                                            Text("Settings")
                                        }
                                    }
                                    IconButton(onClick = { expandedAppBar = true },) {
                                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = LocalStrings.current.moreVert)
                                    }
                                }
                            )
                                 },
                    ) {

                        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
                        val scope = rememberCoroutineScope()
                        ModalBottomSheetLayout(
                            sheetState = state,
                            sheetContent = {
                                LazyColumn {
                                    items(50) {
                                        ListItem(
                                            text = { Text("Item $it") },
                                            icon = {
                                                Icon(
                                                    Icons.Default.AccountCircle,
                                                    contentDescription = "Profile $it"
                                                )
                                            },
                                            modifier = Modifier.clickable {
                                                selectedProfile = "Profile $it"
                                                scope.launch { state.hide() }
                                            }
                                        )
                                    }
                                }
                            }
                        ) {
                            LinearProgressIndicator(modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp),progress = 0.2f)
                            Column(
                                modifier = Modifier
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {

                                CodeDisplay(
                                    modifier = Modifier.weight(1f,fill = true),
                                    code = "abcdef"
                                )
                                ProfileSelector(profileName = selectedProfile
                                ){
                                    scope.launch { state.show() }
                                }
                                PinInputRow()
                            }
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

@Composable
fun CodeDisplay(
    code: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = code,
            style = MaterialTheme.typography.h1
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarrotNeueTheme {
        Greeting("Android")
    }
}

@Composable
fun PinInputRow(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val visualCenterOffset = if (isSystemInDarkTheme()){
        4.dp
    }else{
        0.dp
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f, fill = true)
                .padding(end = 8.dp),
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "PIN") },
            onValueChange = { text = it },
            keyboardActions = KeyboardActions(onAny = { /*TODO*/ }),
            label = { Text("PIN") }
        )
        FloatingActionButton(
            modifier = Modifier.offset(y = visualCenterOffset),
            onClick = { /*TODO*/ },
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "Confirm password")
        }
    }

}

@Composable
fun ProfileSelector(
    profileName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(56.dp)
            .clickable(onClick = onClick)
            .then(modifier),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .weight(weight = 1f, fill = true)
                    .padding(8.dp)) {
                Text(
                    modifier = Modifier.offset(x= 8.dp),
                    text = profileName)
            }
            Icon(modifier = Modifier.padding(8.dp),imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
        }
    }

}