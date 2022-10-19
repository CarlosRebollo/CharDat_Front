package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.framework.CharDatApp

@Composable
fun MainMenu(
    idPersonaje: Int,
    onNavigate: (String) -> Unit,
    viewModel: MainMenuVM = hiltViewModel(),
) {
    viewModel.handleEvent(MainMenuContract.Event.FetchPersonaje(idPersonaje))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF093457)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error,
            )
        }
        viewModel.handleEvent(MainMenuContract.Event.ErrorConsumed)
    }
    LaunchedEffect(Unit) {
        while (true) {
            color.animateTo(Color(0xFF4C0964), animationSpec = tween(5000))
            color.animateTo(Color(0xFF093457), animationSpec = tween(5000))
        }
    }
    CharDatApp {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                val bottomItemIcon = listOf(
                    R.drawable.armas,
                    R.drawable.armaduras,
                    R.drawable.escudos,
                    R.drawable.objetos
                )
                val bottomItemText = listOf("Armas", "Armaduras", "Escudos", "Objetos")
                MainMenuBottomBar(
                    onNavigate = onNavigate,
                    iconItems = bottomItemIcon,
                    textItems = bottomItemText,
                    color = color
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                state.value.personaje?.let { personaje ->
                    MainMenuContent(
                        modifier = Modifier.padding(innerPadding),
                        personaje = personaje,
                        color = color
                    )
                }
            }
        }
    }

}