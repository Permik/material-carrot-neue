package xyz.permik.carrotneue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


// In the general future try to add the proper positioning code to the ActionMenu composable
// This is TEMPORARY
@Composable
fun PositionedActionMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            ActionMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                content = content
            )
        }

    }
}