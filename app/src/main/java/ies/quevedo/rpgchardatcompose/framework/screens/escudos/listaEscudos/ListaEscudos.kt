package ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaEscudos(
    idPersonaje: Int,
    viewModel: ListaEscudosVM = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    viewModel.handleEvent(event = ListaEscudosContract.Event.GetAllEscudos(idPersonaje = idPersonaje))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val color = remember { Animatable(Color(0xFF4C0964)) }
    val colorSecondary = remember { Animatable(Color(0xFFFFC107)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error,
            )
        }
        viewModel.handleEvent(ListaEscudosContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = colorSecondary.value,
                    onClick = {
                        onNavigate(Routes.ADD_ESCUDO + idPersonaje)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "A??adir escudo",
                    )
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaEscudosContent(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    state = state,
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    color = color,
                    onNavigate = onNavigate
                )
            }
        }
    }
}