package ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.domain.Escudo
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaEscudosContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    state: State<ListaEscudosContract.State>,
    viewModel: ListaEscudosVM,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    navController: NavHostController
) {
    val escudosMutables = remember { mutableStateListOf<Escudo>() }
    escudosMutables.clear()
    state.value.listaEscudos?.let { escudos -> escudosMutables.addAll(escudos) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(escudosMutables, key = { it.id }) { escudo ->
                val index = escudosMutables.indexOf(escudo)
                val borrado = SwipeAction(
                    onSwipe = {
                        coroutineScope.launch {
                            escudosMutables.remove(escudo)
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = escudo.name + " eliminado",
                                actionLabel = "DESHACER"
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> viewModel.handleEvent(
                                    ListaEscudosContract.Event.DeleteEscudo(escudo = escudo)
                                )
                                SnackbarResult.ActionPerformed -> {
                                    escudosMutables.add(
                                        index = index,
                                        element = escudo
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
                    swipeThreshold = 175.dp,
                    modifier = Modifier.clip(RoundedCornerShape(30.dp))
                ) {
                    CardItem(
                        arma = null,
                        armadura = null,
                        escudo = escudo,
                        objeto = null,
                        color = color,
                        navController = navController
                    )
                }
                ListItemDivider()
            }
        }
    }
}