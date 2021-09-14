package xyz.permik.carrotneue

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PinInputRow(
    modifier: Modifier = Modifier
) {
    var pin by remember { mutableStateOf("") }
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
            value = pin,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "PIN") },
            onValueChange = { pin = it },
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
fun PinInputRow(
    modifier: Modifier = Modifier,
    pin: String,
    onPinChange: (String) -> Unit,
    onFabClick: () -> Unit
) {
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
            value = pin,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "PIN") },
            onValueChange = onPinChange,
            keyboardActions = KeyboardActions(onAny = { /*TODO*/ }),
            label = { Text("PIN") }
        )
        FloatingActionButton(
            modifier = Modifier.offset(y = visualCenterOffset),
            onClick = onFabClick
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "Confirm password")
        }
    }

}