package ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes

@Composable
fun ListaArmas(
    idPersonaje: Int,
    onNavigate: (String) -> Unit,
    viewModel: ListaArmasVM = hiltViewModel()
) {
    viewModel.handleEvent(event = ListaArmasContract.Event.GetAllArmas(idPersonaje = idPersonaje))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF4C0964)) }
    val colorSecondary = remember { Animatable(Color(0xFFFFC107)) }
    LaunchedEffect(key1 = state.value.armaBorrada) {
        viewModel.handleEvent(event = ListaArmasContract.Event.GetAllArmas(idPersonaje = idPersonaje))
    }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error,
            )
        }
        viewModel.handleEvent(ListaArmasContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = colorSecondary.value,
                    onClick = {
                        onNavigate(Routes.ADD_ARMA + idPersonaje)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir arma",
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaArmasContent(
                    viewModel = viewModel,
                    state = state,
                    modifier = Modifier.padding(innerPadding),
                    color = color,
                    onNavigate = onNavigate
                )
            }
        }
    }
}