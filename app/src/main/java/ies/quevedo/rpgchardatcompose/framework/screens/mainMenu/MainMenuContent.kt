package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun MainMenuContent(
    personaje: Personaje,
    color: Animatable<Color, AnimationVector4D>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color.value)
    ) {
        val imageModifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(Constantes.getImageBanner(personaje.clase)),
            modifier = imageModifier,
            contentDescription = "Clase de personaje",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            text = personaje.name,
            style = androidx.compose.material3.MaterialTheme.typography.displayLarge,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            text = personaje.clase,
            style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        DiceAnimation()
        Spacer(Modifier.height(8.dp))
    }
}