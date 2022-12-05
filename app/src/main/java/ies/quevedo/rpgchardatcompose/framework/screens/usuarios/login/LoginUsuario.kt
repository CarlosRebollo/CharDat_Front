package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login

import androidx.compose.animation.Animatable
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
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.framework.CharDatApp
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen

@Composable
fun LoginUsuario(
    viewModel: LoginUsuarioVM = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF2A1559)) }
    LaunchedEffect(key1 = state.value.usuarioLogueado) {
        if (state.value.usuarioLogueado != null) {
            viewModel.handleEvent(LoginUsuarioContract.Event.InsertUsuarioToken(state.value.usuarioLogueado))
            navController.navigate(route = Screen.ListaPersonajes.mandarToken(state.value.usuarioLogueado!!.token)) {
                popUpTo(route = Screen.ListaPersonajes.route) {
                    inclusive = true
                }
            }
        }
    }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error
            )
        }
        viewModel.handleEvent(LoginUsuarioContract.Event.ErrorConsumed)
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                LoginUsuarioContent(
                    modifier = Modifier.padding(paddingValues),
                    color = color,
                    viewModel = viewModel,
                    navController = navController
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