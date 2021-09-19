package xyz.permik.carrotneue

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import kotlinx.coroutines.launch
import xyz.permik.carrotneue.ui.strings.EnStringsProvider
import xyz.permik.carrotneue.ui.strings.NeueStrings
import xyz.permik.carrotneue.ui.theme.CarrotNeueTheme

class MainActivity : ComponentActivity() {

    private lateinit var lyricist: Lyricist<NeueStrings>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lyricist = rememberStrings()
            CarrotNeueApp(lyricist = lyricist)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(name = "Carrot Neue Main", showBackground = true)
@Composable
fun CarrotNeueApp(
    @PreviewParameter(EnStringsProvider::class) lyricist: Lyricist<NeueStrings>
){
    // Extra layout tasks
    // TODO: Maybe create alt layout to landscape
    // TODO: Maybe create alt layout to dual screens
    // TODO: Maybe create alt layout to foldables

    var expandedActionMenu by remember { mutableStateOf(false) }
    var pinString by remember { mutableStateOf("") }
    // TODO: Persistency
    var selectedProfile by remember { mutableStateOf(OtpProfile(1,"Maintest",secret = ByteArray(16))) }
    var otpProfileList by remember {
        mutableStateOf(
            listOf(
                OtpProfile(1,"Maintest",secret = ByteArray(16)),
                OtpProfile(2,"MainYes",secret = ByteArray(16))
            )
        )
    }
    // TODO: Make the progress bar animate according to remaining valid time (60 s)
    var timeoutB by remember{
        mutableStateOf(true)
    }
    var codeTimeout by remember {
        mutableStateOf(0)
    }
    val codeTimeoutProgress: Float by animateFloatAsState(
        targetValue = if (timeoutB) 1f else 0.0f,
        animationSpec = tween(durationMillis = 60*100, easing = LinearEasing)
    )
    ProvideStrings(lyricist) {
        CarrotNeueTheme {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            PositionedActionMenu(
                expanded = expandedActionMenu,
                onDismissRequest = { expandedActionMenu = false }
            ) {
                // TODO: Navigation to dialog or another view to add a profile
                DropdownMenuItem(onClick = { /* Handle navigation! */ }) {
                    Text("Add Profile")
                }
                // TODO: Navigation to settings
                DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                    Text("Settings")
                }
            }
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text(text = LocalStrings.current.appName)},
                        actions = {
                            // TODO: Add native 'tap and hold to select' -behavior
                            IconButton(onClick = { expandedActionMenu = true },) {
                                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = LocalStrings.current.moreVert)
                            }

                        }
                    )
                },
            ) {

                val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
                val scope = rememberCoroutineScope()
                // TODO: Maybe move the bottom sheet to top-level to overlay the app bar
                ModalBottomSheetLayout(
                    sheetState = state,
                    sheetContent = {
                        // TODO: Maybe integrate profile deletion as an extra button on the items
                        OtpProfileList(
                            profiles = otpProfileList,
                            selectedProfile = selectedProfile,
                            onClick = {
                                selectedProfile = it
                                scope.launch { state.hide() }
                            }
                        )
                    }
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().height(12.dp),
                        progress = codeTimeoutProgress
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // TODO: Add text to clipboard on tap
                        CodeDisplay(
                            modifier = Modifier.weight(1f,fill = true),
                            code = "abcdef"
                        )
                        ProfileSelector(profileName = selectedProfile.name
                        ){
                            scope.launch { state.show() }
                        }
                        PinInputRow(
                            pin = pinString,
                            onPinChange = {
                                if (it.length <= 4){pinString = it}
                                          },
                            onFabClick = { timeoutB = !timeoutB
                            Log.d("dbg", "foo $timeoutB")
                            }
                        )
                    }
                }
            }
        }
    }
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

