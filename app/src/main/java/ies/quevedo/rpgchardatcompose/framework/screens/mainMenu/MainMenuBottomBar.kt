package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes

@Composable
fun MainMenuBottomBar(
    idPersonaje: Int,
    iconItems: List<Int>,
    textItems: List<String>,
    color: Animatable<Color, AnimationVector4D>,
    onNavigate: (String) -> Unit,
) {
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = color.value,
    ) {
        iconItems.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(id = item),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = textItems[index],
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                        textAlign = TextAlign.Center
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (selectedItem) {
                        0 -> onNavigate(Routes.LISTA_ARMAS + idPersonaje)
                        1 -> onNavigate(Routes.LISTA_ARMADURAS + idPersonaje)
                        2 -> onNavigate(Routes.LISTA_ESCUDOS + idPersonaje)
                        3 -> onNavigate(Routes.LISTA_OBJETOS + idPersonaje)
                    }
                }
            )
        }
    }
}