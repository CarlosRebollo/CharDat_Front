package ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma

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

            var nombreArma by remember { mutableStateOf("") }
            var descripcionArma by remember { mutableStateOf("") }
            var turnoArma by remember { mutableStateOf("") }
            var ataqueArma by remember { mutableStateOf("") }
            var danoArma by remember { mutableStateOf("") }
            var paradaArma by remember { mutableStateOf("") }
            var valorArma by remember { mutableStateOf("") }
            var calidadArma by remember { mutableStateOf("") }

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
                    textValue = turnoArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                turnoArma = newTextValue
                                armaEditando.turn = turnoArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("El turno necesita ser un número"))
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
                    textValue = ataqueArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                ataqueArma = newTextValue
                                armaEditando.attackHability = ataqueArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("El ataque necesita ser un número"))
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
                    textValue = danoArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                danoArma = newTextValue
                                armaEditando.damage = danoArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("El daño necesita ser un número"))
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
                    textValue = paradaArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                paradaArma = newTextValue
                                armaEditando.parry = paradaArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("La parada necesita ser un número"))
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
                    textValue = valorArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                valorArma = newTextValue
                                armaEditando.value = valorArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("El valor necesita ser un número"))
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
                    textValue = calidadArma,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                calidadArma = newTextValue
                                armaEditando.quality = calidadArma.toInt()
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddArmaContract.Event.ShowError("La calidad necesita ser un número"))
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
                        guardarArmaYRegresar(
                            idPersonaje = idPersonaje,
                            armaEditando = armaEditando,
                            viewModel = viewModel,
                            onBackPressed = onBackPressed
                        )
                    },
                ) {
                    Text(text = "AÑADIR", fontSize = 16.sp, color = Color.White)
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

fun guardarArmaYRegresar(
    idPersonaje: Int?,
    armaEditando: Arma,
    viewModel: AddArmaVM,
    onBackPressed: () -> Unit
) {
    if (armaEditando.name.isEmpty()) {
        viewModel.handleEvent(AddArmaContract.Event.ShowError("Selecciona un nombre de arma válido"))
    } else {
        if (idPersonaje != null) {
            armaEditando.idPJ = idPersonaje
            viewModel.handleEvent(AddArmaContract.Event.AddArma(armaEditando))
        }
        onBackPressed()
    }
}
