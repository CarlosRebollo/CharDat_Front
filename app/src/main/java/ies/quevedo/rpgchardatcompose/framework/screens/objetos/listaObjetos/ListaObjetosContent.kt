package ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos

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
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaObjetosContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    state: State<ListaObjetosContract.State>,
    viewModel: ListaObjetosVM,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit
) {
    val objetosMutables = remember { mutableStateListOf<Objeto>() }
    objetosMutables.clear()
    state.value.listaObjetos?.let { objetos -> objetosMutables.addAll(objetos) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(objetosMutables, key = { it.id }) { objeto ->
                val index = objetosMutables.indexOf(objeto)
                val borrado = SwipeAction(
                    onSwipe = {
                        coroutineScope.launch {
                            objetosMutables.remove(objeto)
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = objeto.name + " eliminado",
                                actionLabel = "DESHACER"
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> viewModel.handleEvent(
                                    ListaObjetosContract.Event.DeleteObjeto(objeto = objeto)
                                )
                                SnackbarResult.ActionPerformed -> {
                                    objetosMutables.add(
                                        index = index,
                                        element = objeto
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
                    CardItem(
                        arma = null,
                        armadura = null,
                        escudo = null,
                        objeto = objeto,
                        rutaUpdate = Routes.SHOW_OBJETO,
                        onNavigate = onNavigate,
                        color = color
                    )
                }
                ListItemDivider()
            }
        }
    }
}