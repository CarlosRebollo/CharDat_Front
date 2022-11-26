package ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp

@Composable
fun ShowPersonaje(
    idPersonaje: Int,
    viewModel: ShowPersonajeVM = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    viewModel.handleEvent(ShowPersonajeContract.Event.GetPersonaje(id = idPersonaje))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF4C0964)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error
            )
        }
        viewModel.handleEvent(ShowPersonajeContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                state.value.personaje?.let {
                    ShowPersonajeContent(
                        modifier = Modifier.padding(paddingValues),
                        color = color,
                        viewModel = viewModel,
                        personajeParaActualizar = it,
                        onBackPressed = { onBackPressed() },
                    )
                }
            }
        }
    }
}