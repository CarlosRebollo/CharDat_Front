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
            //TODO -> Como los valores han de ser Int porque vienen de un Arma por parametro no dejan dejarlo en "", se pone en 0 y empieza a hacer cosas raras -> Arreglar
            var nombreArma by remember { mutableStateOf(armaEditando.name) }
            var descripcionArma by remember { mutableStateOf(armaEditando.description) }
            var turnoArma by remember { mutableStateOf(armaEditando.turn) }
            var ataqueArma by remember { mutableStateOf(armaEditando.attackHability) }
            var danoArma by remember { mutableStateOf(armaEditando.damage) }
            var paradaArma by remember { mutableStateOf(armaEditando.parry) }
            var valorArma by remember { mutableStateOf(armaEditando.value) }
            var calidadArma by remember { mutableStateOf(armaEditando.quality) }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = nombreArma,
                onValueChange = { newTextValue ->
                    run {
                        nombreArma = newTextValue
                        armaEditando.name = nombreArma
                    }
                },
                list = Constantes.getArmas(),
                color = color,
                label = "Arma",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = descripcionArma,
                onValueChange = { newTextValue ->
                    run {
                        descripcionArma = newTextValue
                        armaEditando.description = descripcionArma
                    }
                },
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
                    textValue = turnoArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                turnoArma = newTextValue.toInt()
                                armaEditando.turn = turnoArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("El turno necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Turno",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = ataqueArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                ataqueArma = newTextValue.toInt()
                                armaEditando.attackHability = ataqueArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("El ataque necesita ser un número"))
                            }
                        }
                    },
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
                    textValue = danoArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                danoArma = newTextValue.toInt()
                                armaEditando.damage = danoArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("El daño necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Daño",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = paradaArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                paradaArma = newTextValue.toInt()
                                armaEditando.parry = paradaArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("La parada necesita ser un número"))
                            }
                        }
                    },
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
                    textValue = valorArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                valorArma = newTextValue.toInt()
                                armaEditando.value = valorArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("El valor necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = calidadArma.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                calidadArma = newTextValue.toInt()
                                armaEditando.quality = calidadArma
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowArmaContract.Event.ShowError("La calidad necesita ser un número"))
                            }
                        }
                    },
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
                        actualizarArmaYRegresar(
                            viewModel = viewModel,
                            onBackPressed = onBackPressed,
                            armaEditando = armaEditando
                        )
                    },
                ) {
                    Text(text = "ACTUALIZAR", fontSize = 16.sp, color = Color.White)
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
