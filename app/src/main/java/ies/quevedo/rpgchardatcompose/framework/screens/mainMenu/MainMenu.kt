package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

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
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.framework.CharDatApp

@Composable
fun MainMenu(
    idPersonaje: Int,
    onNavigate: (String) -> Unit,
    viewModel: MainMenuVM = hiltViewModel(),
) {
    viewModel.handleEvent(MainMenuContract.Event.GetPersonaje(idPersonaje))
    val state = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val color = remember { Animatable(Color(0xFF4C0964)) }
    LaunchedEffect(key1 = state.value.error) {
        state.value.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = error,
            )
        }
        viewModel.handleEvent(MainMenuContract.Event.ErrorConsumed)
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
                    idPersonaje = idPersonaje,
                    onNavigate = onNavigate,
                    iconItems = bottomItemIcon,
                    textItems = bottomItemText,
                    color = color
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                state.value.personaje?.let { personaje ->
                    MainMenuContent(
                        modifier = Modifier.padding(innerPadding),
                        personaje = personaje,
                        color = color,
                        onNavigate = onNavigate
                    )
                }
            }
        }
    }

}