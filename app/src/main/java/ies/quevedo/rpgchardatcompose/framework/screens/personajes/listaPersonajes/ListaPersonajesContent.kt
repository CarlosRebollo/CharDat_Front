package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaPersonajesContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel: ListaPersonajesVM,
    state: State<ListaPersonajesContract.State>,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit
) {
    val personajesMutables = remember { mutableStateListOf<Personaje>() }
    personajesMutables.clear()
    state.value.listaPersonajes?.let { personajes -> personajesMutables.addAll(personajes) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(personajesMutables, key = {it.id}) { personaje ->
                val index = personajesMutables.indexOf(personaje)
                val borrado = SwipeAction(
                    onSwipe = {
                        coroutineScope.launch {
                            personajesMutables.remove(personaje)
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = personaje.name + " eliminado",
                                actionLabel = "DESHACER"
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> viewModel.handleEvent(
                                    ListaPersonajesContract.Event.DeletePersonaje(personaje = personaje)
                                )
                                SnackbarResult.ActionPerformed -> {
                                    personajesMutables.add(
                                        index = index,
                                        element = personaje
                                    )
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.padding(16.dp),
                            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    background = Color.Red
                )
                SwipeableActionsBox(
                    endActions = listOf(borrado),
                    swipeThreshold = 175.dp
                ) {
                    CardPersonaje(
                        personaje = personaje,
                        onNavigate = onNavigate,
                        color = color
                    )
                }
                ListItemDivider()
            }
        }
    }
}