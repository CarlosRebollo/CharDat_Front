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
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen

@Composable
fun BotonesPantallaPrincipal(
    colorSecondary: Animatable<Color, AnimationVector4D>,
    state: State<ListaPersonajesContract.State>,
    viewModel: ListaPersonajesVM,
    navController: NavHostController
) {
    Column {
        if ((state.value.usuarioLogueado?.token ?: Screen.NO_TOKEN) != Screen.NO_TOKEN) {
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
                    if (state.value.listaPersonajes?.isEmpty()!!) {
                        state.value.usuarioLogueado?.let {
                            ListaPersonajesContract.Event.DownloadPersonajes(
                                token = state.value.usuarioLogueado!!.token
                            )
                        }?.let {
                            viewModel.handleEvent(
                                it
                            )
                        }
                        ListaPersonajesContract.Event.RespuestaExitosaConsumed
                    } else {
                        viewModel.handleEvent(ListaPersonajesContract.Event.ShowDialog)
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Importar datos",
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            FloatingActionButton(
                backgroundColor = colorSecondary.value,
                onClick = {
                    viewModel.handleEvent(
                        ListaPersonajesContract.Event.UploadPersonajes(
                            token = state.value.usuarioLogueado?.token!!,
                            personajes = state.value.listaPersonajesCompletos!!
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = "Exportar datos",
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