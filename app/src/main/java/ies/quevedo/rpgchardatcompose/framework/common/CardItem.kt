package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.domain.Escudo
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.theme.AppTypography

@Composable
fun CardItem(
    arma: Arma?,
    armadura: Armadura?,
    escudo: Escudo?,
    objeto: Objeto?,
    rutaUpdate: String,
    onNavigate: (String) -> Unit,
    color: Animatable<Color, AnimationVector4D>
) {
    val typography = AppTypography

    if (arma != null) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .background(Color.Black)
                .clickable {
                    onNavigate(rutaUpdate + arma.id)
                },
        ) {
            Column(
                modifier = Modifier.background(color = color.value),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = arma.name,
                    style = typography.displayLarge,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = arma.description,
                    style = typography.displaySmall,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    } else if (armadura != null) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .background(Color.Black)
                .clickable {
                    onNavigate(rutaUpdate + armadura.id)
                },
        ) {
            Column(
                modifier = Modifier.background(color = color.value),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = armadura.name,
                    style = typography.displayLarge,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = armadura.description,
                    style = typography.displaySmall,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    } else if (escudo != null) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .background(Color.Black)
                .clickable {
                    onNavigate(rutaUpdate + (escudo.id))
                },
        ) {
            Column(
                modifier = Modifier.background(color = color.value),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = escudo.name,
                    style = typography.displayLarge,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = escudo.description,
                    style = typography.displaySmall,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    } else if (objeto != null) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .background(Color.Black)
                .clickable {
                    onNavigate(rutaUpdate + objeto.id)
                },
        ) {
            Column(
                modifier = Modifier.background(color = color.value),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = objeto.name,
                    style = typography.displayLarge,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = objeto.description,
                    style = typography.displaySmall,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}