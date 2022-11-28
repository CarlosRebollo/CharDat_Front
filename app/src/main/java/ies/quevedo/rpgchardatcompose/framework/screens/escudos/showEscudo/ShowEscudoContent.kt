package ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo

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
import ies.quevedo.rpgchardatcompose.domain.Escudo
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextFieldWithDropDownMenu
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun ShowEscudoContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: ShowEscudoVM,
    escudoParaActualizar: Escudo,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val escudoEditando by remember { mutableStateOf(escudoParaActualizar) }

            val nombreEscudoInit = escudoParaActualizar.name
            val descripcionEscudoInit = escudoParaActualizar.description
            val ataqueEscudoInit = escudoParaActualizar.attackHability
            val danoEscudoInit = escudoParaActualizar.damage
            val paradaEscudoInit = escudoParaActualizar.parry
            val valorEscudoInit = escudoParaActualizar.value
            val calidadEscudoInit = escudoParaActualizar.quality
            var nombreEscudo by remember { mutableStateOf(nombreEscudoInit) }
            var descripcionEscudo by remember { mutableStateOf(descripcionEscudoInit) }
            var ataqueEscudo by remember { mutableStateOf(ataqueEscudoInit.toString()) }
            var danoEscudo by remember { mutableStateOf(danoEscudoInit.toString()) }
            var paradaEscudo by remember { mutableStateOf(paradaEscudoInit.toString()) }
            var valorEscudo by remember { mutableStateOf(valorEscudoInit.toString()) }
            var calidadEscudo by remember { mutableStateOf(calidadEscudoInit.toString()) }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = nombreEscudo,
                onValueChange = { newTextValue -> nombreEscudo = newTextValue },
                list = Constantes.getArmas(),
                color = color,
                label = "Escudo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = descripcionEscudo,
                onValueChange = { newValue -> descripcionEscudo = newValue },
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
                    textValue = ataqueEscudo,
                    onValueChange = { newTextValue -> ataqueEscudo = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Ataque",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = danoEscudo,
                    onValueChange = { newTextValue -> danoEscudo = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Daño",
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
                    textValue = paradaEscudo,
                    onValueChange = { newTextValue -> paradaEscudo = newTextValue },
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
                    textValue = valorEscudo,
                    onValueChange = { newTextValue -> valorEscudo = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = calidadEscudo,
                    onValueChange = { newTextValue -> calidadEscudo = newTextValue },
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
                            escudoEditando.name = nombreEscudo
                            escudoEditando.description = descripcionEscudo
                            escudoEditando.attackHability = ataqueEscudo.toInt()
                            escudoEditando.damage = danoEscudo.toInt()
                            escudoEditando.parry = paradaEscudo.toInt()
                            escudoEditando.value = valorEscudo.toInt()
                            escudoEditando.quality = calidadEscudo.toInt()
                            actualizarEscudoYRegresar(
                                viewModel = viewModel,
                                onBackPressed = onBackPressed,
                                escudoEditando = escudoEditando
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                ShowEscudoContract.Event.ShowError("Los campos numéricos no pueden contener letras")
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

fun actualizarEscudoYRegresar(
    viewModel: ShowEscudoVM,
    onBackPressed: () -> Unit,
    escudoEditando: Escudo
) {
    if (escudoEditando.name.isEmpty()) {
        viewModel.handleEvent(ShowEscudoContract.Event.ShowError("Selecciona un arma válida"))
    } else {
        viewModel.handleEvent(ShowEscudoContract.Event.UpdateEscudo(escudo = escudoEditando))
        onBackPressed()
    }
}
