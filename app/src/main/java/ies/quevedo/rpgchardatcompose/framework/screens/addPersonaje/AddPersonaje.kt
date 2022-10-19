package ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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
import ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje.AddPersonajeContract.Event

@Composable
fun AddPersonaje(
    returnToList: () -> Unit,
    viewModel: AddPersonajeVM = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF093457)) }
    LaunchedEffect(Unit) {
        while (true) {
            color.animateTo(Color(0xFF4C0964), animationSpec = tween(5000))
            color.animateTo(Color(0xFF093457), animationSpec = tween(5000))
        }
    }
    LaunchedEffect(key1 = state.value.success) {
        if (state.value.success) {
            returnToList()
        }
    }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error
            )
        }
        viewModel.handleEvent(Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                AddPersonajeContent(
                    modifier = Modifier.padding(paddingValues),
                    color = color,
                    onBackPressed = { returnToList() },
                    viewModel = viewModel,
                )
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}