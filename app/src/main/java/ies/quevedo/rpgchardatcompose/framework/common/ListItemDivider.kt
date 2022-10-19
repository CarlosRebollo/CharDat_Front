package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}