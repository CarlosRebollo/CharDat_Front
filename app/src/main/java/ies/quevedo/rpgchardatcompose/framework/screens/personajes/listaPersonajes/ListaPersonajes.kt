package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.common.MultiFloatingState
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes.ListaPersonajesContract.Event
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaPersonajes(
    onNavigate: (String) -> Unit,
    viewModel: ListaPersonajesVM = hiltViewModel()
) {
    viewModel.handleEvent(event = Event.GetAllPersonajes)
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val color = remember { Animatable(Color(0xFF4C0964)) }
    val colorSecondary = remember { Animatable(Color(0xFFFFC107)) }
    val multiFloatingState by remember { mutableStateOf(MultiFloatingState.Collapsed) }
    LaunchedEffect(key1 = state.value.listaPersonajes) {
        viewModel.handleEvent(Event.GetAllPersonajes)
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
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaPersonajesContent(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    onNavigate = onNavigate,
                    state = state,
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