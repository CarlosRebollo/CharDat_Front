package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras

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
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListaArmaduras(
    idPersonaje: Int,
    viewModel: ListaArmadurasVM = hiltViewModel(),
    navController: NavHostController
) {
    viewModel.handleEvent(event = ListaArmadurasContract.Event.GetAllArmaduras(idPersonaje = idPersonaje))
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
        viewModel.handleEvent(ListaArmadurasContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = colorSecondary.value,
                    onClick = {
                        navController.navigate(Screen.AddArmadura.mandarIdPersonaje(idPersonaje))
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir armadura",
                    )
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ListaArmadurasContent(
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope,
                    state = state,
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    color = color,
                    navController = navController
                )
            }
        }
    }
}