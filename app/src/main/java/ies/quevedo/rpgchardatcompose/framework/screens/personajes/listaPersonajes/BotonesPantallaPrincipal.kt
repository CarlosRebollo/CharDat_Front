package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes

@Composable
fun BotonesPantallaPrincipal(
    correoUsuario: String?,
    colorSecondary: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit
) {
    Column {
        if (correoUsuario != null) {
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.LISTA_PERSONAJES)
                }) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Cerrar sesión",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.ADD_PERSONAJE)
                }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Subir datos",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.ADD_PERSONAJE)
                }) {
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = "Importar datos",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.ADD_PERSONAJE)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir personaje",
                )
            }
        } else {
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.LOGIN)
                }) {
                Icon(
                    imageVector = Icons.Default.Login,
                    contentDescription = "Iniciar sesión",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    onNavigate(Routes.ADD_PERSONAJE)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir personaje",
                )
            }
        }
    }
}