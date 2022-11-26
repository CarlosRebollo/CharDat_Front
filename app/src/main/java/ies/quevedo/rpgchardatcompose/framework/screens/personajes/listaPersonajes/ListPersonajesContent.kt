package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

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
import ies.quevedo.rpgchardatcompose.framework.common.ListItemDivider

@Composable
fun ListPersonajesContent(
    modifier: Modifier,
    onNavigate: (String) -> Unit,
    personajes: State<ListaPersonajesContract.State>,
    color: Animatable<Color, AnimationVector4D>
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(personajes.value.listaPersonajes ?: emptyList()) { personaje ->
                CardPersonaje(
                    personaje = personaje,
                    onNavigate = onNavigate,
                    color = color
                )
                ListItemDivider()
            }
        }
    }
}