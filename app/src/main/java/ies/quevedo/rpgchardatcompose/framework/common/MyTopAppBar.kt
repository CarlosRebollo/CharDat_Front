package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import ies.quevedo.rpgchardatcompose.framework.screens.mainMenu.MainMenuContract

@Composable
fun MyTopAppBar(
    personaje: State<MainMenuContract.State>? = null,
    color: Animatable<Color, AnimationVector4D>,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        backgroundColor = color.value,
        title = {
            Text(
                text = personaje?.let { it.value.personaje?.name } ?: "",
                color = Color.White
            )
        },
        navigationIcon = {
            GoBack { onBackPressed() }
        }
    )
}

@Composable
fun GoBack(back: () -> Unit) {
    IconButton(onClick = back) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White
        )
    }
}