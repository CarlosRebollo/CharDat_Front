package ies.quevedo.rpgchardatcompose.framework.screens.escudos.addEscudo

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
fun AddEscudoContent(
    idPersonaje: Int?,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddEscudoVM,
    onBackPressed: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val escudoEditando by remember { mutableStateOf(Escudo()) }

            var nombreEscudo by remember { mutableStateOf("") }
            var descripcionEscudo by remember { mutableStateOf("") }
            var ataqueEscudo by remember { mutableStateOf("") }
            var danoEscudo by remember { mutableStateOf("") }
            var paradaEscudo by remember { mutableStateOf("") }
            var valorEscudo by remember { mutableStateOf("") }
            var calidadEscudo by remember { mutableStateOf("") }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = nombreEscudo,
                onValueChange = { newTextValue -> nombreEscudo = newTextValue },
                list = Constantes.getEscudos(),
                color = color,
                label = "Escudo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = descripcionEscudo,
                onValueChange = { newTextValue -> descripcionEscudo = newTextValue },
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
                    modifier = Modifier.width(150.dp),
                    label = "Ataque",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = danoEscudo,
                    onValueChange = { newTextValue -> danoEscudo = newTextValue },
                    modifier = Modifier.width(150.dp),
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
                    modifier = Modifier.width(150.dp),
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
                    modifier = Modifier.width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = calidadEscudo,
                    onValueChange = { newTextValue -> calidadEscudo = newTextValue },
                    modifier = Modifier.width(150.dp),
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
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        try {
                            if (ataqueEscudo == "") ataqueEscudo = "0"
                            if (danoEscudo == "") danoEscudo = "0"
                            if (paradaEscudo == "") paradaEscudo = "0"
                            if (valorEscudo == "") valorEscudo = "0"
                            if (calidadEscudo == "") calidadEscudo = "0"
                            escudoEditando.name = nombreEscudo
                            escudoEditando.description = descripcionEscudo
                            escudoEditando.attackHability = ataqueEscudo.toInt()
                            escudoEditando.damage = danoEscudo.toInt()
                            escudoEditando.parry = paradaEscudo.toInt()
                            escudoEditando.value = valorEscudo.toInt()
                            escudoEditando.quality = calidadEscudo.toInt()
                            guardarEscudoYRegresar(
                                idPersonaje = idPersonaje,
                                escudoEditando = escudoEditando,
                                viewModel = viewModel,
                                onBackPressed = onBackPressed
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                AddEscudoContract.Event.ShowError("Los campos numéricos no pueden contener letras")
                            )
                        }
                    },
                ) {
                    Text(text = "AÑADIR", fontSize = 16.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(modifier = Modifier.width(120.dp), onClick = { onBackPressed() }) {
                    Text(text = "CANCELAR", fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun guardarEscudoYRegresar(
    idPersonaje: Int?,
    escudoEditando: Escudo,
    viewModel: AddEscudoVM,
    onBackPressed: () -> Unit
) {
    if (escudoEditando.name.isEmpty()) {
        viewModel.handleEvent(AddEscudoContract.Event.ShowError("Selecciona un nombre de escudo válido"))
    } else {
        if (idPersonaje != null) {
            escudoEditando.idPJ = idPersonaje
            viewModel.handleEvent(AddEscudoContract.Event.AddEscudo(escudo = escudoEditando))
        } else {
            viewModel.handleEvent(AddEscudoContract.Event.ShowError("Error obteniendo los datos del personaje"))
        }
        onBackPressed()
    }
}
