package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras

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
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaArmadurasContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    state: State<ListaArmadurasContract.State>,
    viewModel: ListaArmadurasVM,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    navController: NavHostController
) {
    val armadurasMutables = remember { mutableStateListOf<Armadura>() }
    armadurasMutables.clear()
    state.value.listaArmaduras?.let { armaduras -> armadurasMutables.addAll(armaduras) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(armadurasMutables, key = { it.id }) { armadura ->
                val index = armadurasMutables.indexOf(armadura)
                val borrado = SwipeAction(
                    onSwipe = {
                        coroutineScope.launch {
                            armadurasMutables.remove(armadura)
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = armadura.name + " eliminado",
                                actionLabel = "DESHACER"
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> viewModel.handleEvent(
                                    ListaArmadurasContract.Event.DeleteArmadura(
                                        armadura
                                    )
                                )
                                SnackbarResult.ActionPerformed -> {
                                    armadurasMutables.add(
                                        index = index,
                                        element = armadura
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
                        armadura = armadura,
                        escudo = null,
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