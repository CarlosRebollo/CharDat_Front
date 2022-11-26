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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ies.quevedo.rpgchardatcompose.R
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ListaArmasContent(
    viewModel: ListaArmasVM,
    state: State<ListaArmasContract.State>,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.value.listaArmas ?: emptyList()) { arma ->
                val borrado = SwipeAction(
                    onSwipe = {
                        viewModel.handleEvent(ListaArmasContract.Event.DeleteArma(arma = arma))
                        // TODO -> Hacer el UNDO del Snackbar
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
                    swipeThreshold = 100.dp
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