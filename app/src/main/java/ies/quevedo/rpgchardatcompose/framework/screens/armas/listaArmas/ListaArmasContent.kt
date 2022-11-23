package ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ies.quevedo.rpgchardatcompose.framework.common.CardItem
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes

@Composable
fun ListaArmasContent(
    modifier: Modifier,
    onNavigate: (String) -> Unit,
    armas: State<ListaArmasContract.State>,
    color: Animatable<Color, AnimationVector4D>
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(armas.value.listaArmas ?: emptyList()) { arma ->
                CardItem(
                    arma = arma,
                    armadura = null,
                    escudo = null,
                    objeto = null,
                    rutaUpdate = Routes.SHOW_ARMA,
                    onNavigate = onNavigate,
                    color = color
                )
                ListItemDivider()
            }
        }
    }
}