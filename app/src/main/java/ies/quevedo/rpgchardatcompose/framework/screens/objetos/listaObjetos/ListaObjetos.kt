package ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos.ListaEscudosContent
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaObjetos(
    idPersonaje: Int,
    viewModel: ListaObjetosVM = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    viewModel.handleEvent(event = ListaObjetosContract.Event.GetAllObjetos(idPersonaje = idPersonaje))
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
        viewModel.handleEvent(ListaObjetosContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = colorSecondary.value,
                    onClick = {
                        onNavigate(Routes.ADD_OBJETO + idPersonaje)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir objeto",
                    )
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaObjetosContent(
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