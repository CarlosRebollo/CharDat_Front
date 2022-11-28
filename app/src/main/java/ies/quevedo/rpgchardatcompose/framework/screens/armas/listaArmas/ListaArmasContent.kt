package ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas

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
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaArmasContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel: ListaArmasVM,
    state: State<ListaArmasContract.State>,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit
) {
    val armasMutables = remember { mutableStateListOf<Arma>() }
    armasMutables.clear()
    state.value.listaArmas?.let { armas -> armasMutables.addAll(armas) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(armasMutables, key = { it.id }) { arma ->
                val index = armasMutables.indexOf(arma)
                val borrado = SwipeAction(
                    onSwipe = {
                        coroutineScope.launch {
                            armasMutables.remove(arma)
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = arma.name + " eliminado",
                                actionLabel = "DESHACER"
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> viewModel.handleEvent(
                                    ListaArmasContract.Event.DeleteArma(
                                        arma
                                    )
                                )
                                SnackbarResult.ActionPerformed -> {
                                    armasMutables.add(
                                        index = index,
                                        element = arma
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
                        arma = arma,
                        armadura = null,
                        escudo = null,
                        objeto = null,
                        rutaUpdate = Routes.SHOW_ARMA,
                        onNavigate = onNavigate,
                        color = color
                    )
                }
                ListItemDivider()
            }
        }
    }
}