package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura

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
fun AddArmaduraContent(
    idPersonaje: Int?,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddArmaduraVM,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val armaduraEditando by remember { mutableStateOf(Armadura()) }

            var nombreArmadura by remember { mutableStateOf("") }
            var descripcionArmadura by remember { mutableStateOf("") }
            var llevarArmadura by remember { mutableStateOf("") }
            var ta by remember { mutableStateOf("") }
            var valorArmadura by remember { mutableStateOf("") }
            var calidadArmadura by remember { mutableStateOf("") }

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
                            if (llevarArmadura == "") llevarArmadura = "0"
                            if (ta == "") ta = "0"
                            if (valorArmadura == "") valorArmadura = "0"
                            if (calidadArmadura == "") calidadArmadura = "0"
                            armaduraEditando.name = nombreArmadura
                            armaduraEditando.description = descripcionArmadura
                            armaduraEditando.armor = llevarArmadura.toInt()
                            armaduraEditando.ta = ta.toInt()
                            armaduraEditando.value = valorArmadura.toInt()
                            armaduraEditando.quality = calidadArmadura.toInt()
                            guardarArmaduraYRegresar(
                                idPersonaje = idPersonaje,
                                armaduraEditando = armaduraEditando,
                                viewModel = viewModel,
                                onBackPressed = onBackPressed
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                AddArmaduraContract.Event.ShowError("Los campos numéricos no pueden contener letras")
                            )
                        }
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

fun guardarArmaduraYRegresar(
    idPersonaje: Int?,
    armaduraEditando: Armadura,
    viewModel: AddArmaduraVM,
    onBackPressed: () -> Unit
) {
    if (armaduraEditando.name.isEmpty()) {
        viewModel.handleEvent(AddArmaduraContract.Event.ShowError("Selecciona un nombre de armadura válido"))
    } else {
        if (idPersonaje != null) {
            armaduraEditando.idPJ = idPersonaje
            viewModel.handleEvent(AddArmaduraContract.Event.AddArmadura(armaduraEditando))
        } else {
            viewModel.handleEvent(AddArmaduraContract.Event.ShowError("Error obteniendo los datos del personaje"))
        }
        onBackPressed()
    }
}