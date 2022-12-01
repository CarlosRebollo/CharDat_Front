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
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen

@Composable
fun BotonesPantallaPrincipal(
    colorSecondary: Animatable<Color, AnimationVector4D>,
    token: String?,
    navController: NavHostController
) {
    Column {
        if (token != Screen.NO_TOKEN) {
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    navController.navigate(Screen.ListaPersonajes.mandarToken(Screen.NO_TOKEN)) {
                        popUpTo(Screen.ListaPersonajes.route) {
                            inclusive = true
                        }
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Cerrar sesión",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Subir datos",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = "Importar datos",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    navController.navigate(Screen.AddPersonaje.route)
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
                    navController.navigate(Screen.LoginUsuario.route)
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
                    navController.navigate(Screen.AddPersonaje.route)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir personaje",
                )
            }
        }
    }
}