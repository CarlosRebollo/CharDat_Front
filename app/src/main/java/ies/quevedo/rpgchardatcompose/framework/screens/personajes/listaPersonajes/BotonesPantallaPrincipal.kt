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
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen

@Composable
fun BotonesPantallaPrincipal(
    colorSecondary: Animatable<Color, AnimationVector4D>,
    usuarioLogueado: UsuarioEntity?,
    state: State<ListaPersonajesContract.State>,
    viewModel: ListaPersonajesVM,
    navController: NavHostController
) {
    Column {
        if ((usuarioLogueado?.token ?: Screen.NO_TOKEN) != Screen.NO_TOKEN) {
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    viewModel.handleEvent(ListaPersonajesContract.Event.BorrarTokenLocal)
                    navController.navigate(Screen.ListaPersonajes.route) {
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
                onClick = {
                    usuarioLogueado?.let {
                        ListaPersonajesContract.Event.DownloadPersonajes(
                            token = usuarioLogueado.token
                        )
                    }?.let {
                        viewModel.handleEvent(
                            it
                        )
                    }
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
                    state.value.listaPersonajes?.let { personajes ->
                        usuarioLogueado?.let {
                            ListaPersonajesContract.Event.UploadPersonajes(
                                token = it.token,
                                personajes = personajes
                            )
                        }
                    }?.let { event -> viewModel.handleEvent(event) }
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