package ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes.ListaPersonajesContract.Event

@Composable
fun ListPersonajes(
    onNavigate: (String) -> Unit,
    viewModel: ListaPersonajesVM = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF093457)) }
    val colorSecondary = remember { Animatable(Color(0xFF4C0964)) }
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
                ListPersonajesContent(
                    modifier = Modifier.padding(innerPadding),
                    onNavigate = onNavigate,
                    personajes = state,
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