package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaPersonajes(
    viewModel: ListaPersonajesVM = hiltViewModel(),
    navController: NavHostController,
    token: String
) {
    viewModel.handleEvent(event = Event.GetAllPersonajes)
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val color = remember { Animatable(Color(0xFF2A1559)) }
    val colorSecondary = remember { Animatable(Color(0xFFE1B954)) }
    LaunchedEffect(key1 = state.value.listaPersonajes) {
        viewModel.handleEvent(Event.GetAllPersonajes)
    }
    LaunchedEffect(key1 = state.value.listaPersonajesDescargados) {
        if (state.value.listaPersonajesDescargados != null) {
            viewModel.handleEvent(Event.GetAllPersonajes)
            viewModel.handleEvent(Event.DeleteAllRoom(listaPersonajes = state.value.listaPersonajes))
            viewModel.handleEvent(Event.InsertAllRoom(listaPersonajes = state.value.listaPersonajesDescargados))
        }
    }
    LaunchedEffect(key1 = state.value.respuestaExitosaUpload) {
        if (state.value.respuestaExitosaUpload) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Personajes guardados en la nube con éxito",
            )
        }
        viewModel.handleEvent(Event.RespuestaExitosaConsumed)
    }
    LaunchedEffect(key1 = state.value.respuestaExitosaDownload) {
        if (state.value.respuestaExitosaDownload) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Personajes sincronizados",
            )
        }
        viewModel.handleEvent(Event.RespuestaExitosaConsumed)
    }
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
                    token = token,
                    state = state,
                    viewModel = viewModel,
                    colorSecondary = colorSecondary,
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