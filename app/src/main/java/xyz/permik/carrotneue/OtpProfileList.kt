package xyz.permik.carrotneue

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtpProfileList(
    profiles: List<OtpProfile>,
    selectedProfile: OtpProfile,
    onClick: (OtpProfile) -> Unit
) {
    LazyColumn {
        items(profiles){ profile ->
            ListItem(
                text = { Text("Item ${profile.name}") },
                icon = {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Profile ${profile.name}"
                    )
                },
                modifier = Modifier.clickable(onClick = { onClick(profile) })
            )
        }
    }
}