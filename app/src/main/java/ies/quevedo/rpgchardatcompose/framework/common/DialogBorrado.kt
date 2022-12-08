package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DialogBorrado(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        backgroundColor = Color(0xFF2A1559),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "OK", color = Color(0xFFE1B954))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "CANCELAR", color = Color.Red)
            }
        },
        title = { Text(text = "AVISO", color = Color.White) },
        text = {
            Text(
                text = "Los datos importados se sobreescribirán sobre los actuales. ¿Continuar?",
                color = Color.White
            )
        }
    )
}