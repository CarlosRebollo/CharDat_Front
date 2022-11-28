package ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextFieldWithDropDownMenu
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun ShowArmaContent(
    armaParaActualizar: Arma,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: ShowArmaVM,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val armaEditando by remember { mutableStateOf(armaParaActualizar) }

            val nombreArmaInit = armaParaActualizar.name
            val descripcionArmaInit = armaParaActualizar.description
            val turnoArmaInit = armaParaActualizar.turn
            val ataqueArmaInit = armaParaActualizar.attackHability
            val danoArmaInit = armaParaActualizar.damage
            val paradaArmaInit = armaParaActualizar.parry
            val valorArmaInit = armaParaActualizar.value
            val calidadArmaInit = armaParaActualizar.quality
            var nombreArma by remember { mutableStateOf(nombreArmaInit) }
            var descripcionArma by remember { mutableStateOf(descripcionArmaInit) }
            var turnoArma by remember { mutableStateOf(turnoArmaInit.toString()) }
            var ataqueArma by remember { mutableStateOf(ataqueArmaInit.toString()) }
            var danoArma by remember { mutableStateOf(danoArmaInit.toString()) }
            var paradaArma by remember { mutableStateOf(paradaArmaInit.toString()) }
            var valorArma by remember { mutableStateOf(valorArmaInit.toString()) }
            var calidadArma by remember { mutableStateOf(calidadArmaInit.toString()) }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = nombreArma,
                onValueChange = { newTextValue -> nombreArma = newTextValue },
                list = Constantes.getArmas(),
                color = color,
                label = "Arma",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = descripcionArma,
                onValueChange = { newValue -> descripcionArma = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Descripción",
                keyboardType = KeyboardType.Text,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextField(
                    textValue = turnoArma,
                    onValueChange = { newTextValue -> turnoArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Turno",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = ataqueArma,
                    onValueChange = { newTextValue -> ataqueArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Ataque",
                    keyboardType = KeyboardType.Text,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextField(
                    textValue = danoArma,
                    onValueChange = { newTextValue -> danoArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Daño",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = paradaArma,
                    onValueChange = { newTextValue -> paradaArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Parada",
                    keyboardType = KeyboardType.Text,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextField(
                    textValue = valorArma,
                    onValueChange = { newTextValue -> valorArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = calidadArma,
                    onValueChange = { newTextValue -> calidadArma = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Calidad",
                    keyboardType = KeyboardType.Text,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 70.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier
                        .width(120.dp),
                    onClick = {
                        try {
                            armaEditando.name = nombreArma
                            armaEditando.description = descripcionArma
                            armaEditando.turn = turnoArma.toInt()
                            armaEditando.attackHability = ataqueArma.toInt()
                            armaEditando.damage = danoArma.toInt()
                            armaEditando.parry = paradaArma.toInt()
                            armaEditando.value = valorArma.toInt()
                            armaEditando.quality = calidadArma.toInt()
                            actualizarArmaYRegresar(
                                viewModel = viewModel,
                                onBackPressed = onBackPressed,
                                armaEditando = armaEditando
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                ShowArmaContract.Event.ShowError("Los campos numéricos no pueden contener letras"))
                        }
                    },
                ) {
                    Text(text = "MODIFICAR", fontSize = 16.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier
                        .width(120.dp),
                    onClick = { onBackPressed() }
                ) {
                    Text(text = "CANCELAR", fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun actualizarArmaYRegresar(
    viewModel: ShowArmaVM,
    onBackPressed: () -> Unit,
    armaEditando: Arma
) {
    if (armaEditando.name.isEmpty()) {
        viewModel.handleEvent(ShowArmaContract.Event.ShowError("Selecciona un arma válida"))
    } else {
        viewModel.handleEvent(ShowArmaContract.Event.UpdateArma(arma = armaEditando))
        onBackPressed()
    }
}
