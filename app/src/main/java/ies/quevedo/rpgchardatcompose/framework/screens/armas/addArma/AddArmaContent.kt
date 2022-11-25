package ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ies.quevedo.rpgchardatcompose.domain.Arma


@Composable
fun AddArmaContent(
    idPersonaje: Int?,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddArmaVM,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val armaEditando by remember { mutableStateOf(Arma()) }
        }
    }
}