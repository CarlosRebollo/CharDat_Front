package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura

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
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextFieldWithDropDownMenu
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun ShowArmaduraContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: ShowArmaduraVM,
    armaduraParaActualizar: Armadura,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val armaduraEditando by remember { mutableStateOf(armaduraParaActualizar) }

            val nombreArmaduraInit = armaduraParaActualizar.name
            val descripcionArmaduraInit = armaduraParaActualizar.description
            val armaduraInit = armaduraParaActualizar.armor
            val taInit = armaduraParaActualizar.ta
            val valorArmaduraInit = armaduraParaActualizar.value
            val calidadArmaduraInit = armaduraParaActualizar.quality
            var nombreArmadura by remember { mutableStateOf(nombreArmaduraInit) }
            var descripcionArmadura by remember { mutableStateOf(descripcionArmaduraInit) }
            var llevarArmadura by remember { mutableStateOf(armaduraInit.toString()) }
            var ta by remember { mutableStateOf(taInit.toString()) }
            var valorArmadura by remember { mutableStateOf(valorArmaduraInit.toString()) }
            var calidadArmadura by remember { mutableStateOf(calidadArmaduraInit.toString()) }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = nombreArmadura,
                onValueChange = { newTextValue -> nombreArmadura = newTextValue },
                list = Constantes.getArmaduras(),
                color = color,
                label = "Armadura",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = descripcionArmadura,
                onValueChange = { newTextValue -> descripcionArmadura = newTextValue },
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
                    textValue = llevarArmadura,
                    onValueChange = { newTextValue -> llevarArmadura = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Llevar armadura",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = ta,
                    onValueChange = { newTextValue -> ta = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "TA",
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
                    textValue = valorArmadura,
                    onValueChange = { newTextValue -> valorArmadura = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = calidadArmadura,
                    onValueChange = { newTextValue -> calidadArmadura = newTextValue },
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
                            armaduraEditando.name = nombreArmadura
                            armaduraEditando.description = descripcionArmadura
                            armaduraEditando.armor = llevarArmadura.toInt()
                            armaduraEditando.ta = ta.toInt()
                            armaduraEditando.value = valorArmadura.toInt()
                            armaduraEditando.quality = calidadArmadura.toInt()
                            actualizarArmaduraYRegresar(
                                viewModel = viewModel,
                                onBackPressed = onBackPressed,
                                armaduraEditando = armaduraEditando
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                ShowArmaduraContract.Event.ShowError("Los campos numéricos no pueden contener letras")
                            )
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

fun actualizarArmaduraYRegresar(
    armaduraEditando: Armadura,
    viewModel: ShowArmaduraVM,
    onBackPressed: () -> Unit
) {
    if (armaduraEditando.name.isEmpty()) {
        viewModel.handleEvent(ShowArmaduraContract.Event.ShowError("Selecciona un arma válida"))
    } else {
        viewModel.handleEvent(ShowArmaduraContract.Event.UpdateArma(armadura = armaduraEditando))
        onBackPressed()
    }
}
