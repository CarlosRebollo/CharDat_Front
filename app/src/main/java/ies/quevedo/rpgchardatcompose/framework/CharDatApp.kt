package ies.quevedo.rpgchardatcompose.framework

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import ies.quevedo.rpgchardatcompose.framework.theme.AppTheme

@Composable
fun CharDatApp(content: @Composable() () -> Unit) {
    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}