package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import ies.quevedo.rpgchardatcompose.framework.theme.AppTypography
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes.getImageBannerMini

@Composable
fun CardPersonaje(
    personaje: Personaje,
    onNavigate: (String) -> Unit,
    color: Animatable<Color, AnimationVector4D>
) {
    val typography = AppTypography

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .background(Color.Black)
            .clickable {
                onNavigate(Routes.MAIN_MENU + personaje.id)
            },
    ) {
        Column(
            modifier = Modifier.background(color = color.value),
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = color.value)
                .clip(shape = RoundedCornerShape(20.dp))
            Image(
                painter = painterResource(getImageBannerMini(personaje.clase)),
                modifier = imageModifier,
                contentDescription = "Clase de personaje",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                text = personaje.name,
                style = typography.displayLarge,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                text = personaje.clase,
                style = typography.displaySmall,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}