package ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma

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
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.CharDatApp

@Composable
fun ShowArma(
    idArma: Int,
    viewModel: ShowArmaVM = hiltViewModel(),
    navController: NavHostController
) {
    viewModel.handleEvent(ShowArmaContract.Event.GetArma(idArma = idArma))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF2A1559)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error
            )
        }
        viewModel.handleEvent(ShowArmaContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                state.value.arma?.let {
                    ShowArmaContent(
                        modifier = Modifier.padding(paddingValues),
                        color = color,
                        viewModel = viewModel,
                        armaParaActualizar = it,
                        navController = navController
                    )
                }
            }
        }
    }
}