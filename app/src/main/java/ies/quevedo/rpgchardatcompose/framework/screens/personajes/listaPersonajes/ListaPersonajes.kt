package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import android.widget.Toast
import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.common.DialogBorrado
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaPersonajes(
    viewModel: ListaPersonajesVM = hiltViewModel(),
    navController: NavHostController
) {
    viewModel.handleEvent(event = Event.GetTokenLocal)
    viewModel.handleEvent(event = Event.GetAllPersonajes)
    viewModel.handleEvent(event = Event.GetAllPersonajesConObjetos)
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val color = remember { Animatable(Color(0xFF2A1559)) }
    val colorSecondary = remember { Animatable(Color(0xFFE1B954)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error,
            )
        }
        viewModel.handleEvent(Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                BotonesPantallaPrincipal(
                    colorSecondary = colorSecondary,
                    state = state,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaPersonajesContent(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    viewModel = viewModel,
                    state = state,
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    color = color
                )
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp),
                        color = color.value
                    )
                }
                if (state.value.showDialog) {
                    DialogBorrado(
                        onDismiss = { viewModel.handleEvent(Event.DismissDialog) },
                        onConfirm = {
                            viewModel.handleEvent(Event.DeleteAllRoom)
                            state.value.usuarioLogueado?.let {
                                Event.DownloadPersonajes(
                                    token = state.value.usuarioLogueado!!.token
                                )
                            }?.let {
                                viewModel.handleEvent(
                                    it
                                )
                            }
                            viewModel.handleEvent(Event.DismissDialog)
                        })
                }
                if (state.value.respuestaExitosaDownload) {
                    Toast.makeText(
                        LocalContext.current,
                        "Personajes sincronizados",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.handleEvent(Event.RespuestaExitosaConsumed)
                }
                if (state.value.respuestaExitosaUpload) {
                    Toast.makeText(
                        LocalContext.current,
                        "Personajes guardados",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.handleEvent(Event.RespuestaExitosaConsumed)
                }
            }
        }
    }
}

/*
    EFECTO DE ANIMACIÓN DE COLOR

    LaunchedEffect(Unit) {
        while (true) {
            color.animateTo(Color(0xFF4C0964), animationSpec = tween(5000))
            color.animateTo(Color(0xFF093457), animationSpec = tween(5000))
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            colorSecondary.animateTo(Color(0xFF093457), animationSpec = tween(5000))
            colorSecondary.animateTo(Color(0xFF4C0964), animationSpec = tween(5000))
        }
    }
    */