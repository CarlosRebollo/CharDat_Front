package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DialogBorrado(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "OK", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "CANCELAR", color = Color.Red)
            }
        },
        title = { Text(text = "AVISO") },
        text = { Text(text = "Los datos importados se sobreescribirán sobre los actuales. ¿Continuar?") }
    )
}