package ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextFieldWithDropDownMenu
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun AddPersonajeContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    onBackPressed: () -> Unit,
    viewModel: AddPersonajeVM
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)

    ) {
        item {
            MyOutlinedTextFieldWithDropDownMenu(
                textValue = viewModel.personaje.collectAsState().value.clase,
                onValueChange = { newTextValue -> viewModel.setPersonaje(Personaje(clase = newTextValue)) },
                list = Constantes.getClases(),
                color = color,
                label = "Clase del personaje",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )
            MyOutlinedTextField(
                textValue = viewModel.personaje.collectAsState().value.name,
                onValueChange = { newTextValue -> viewModel.setPersonaje(Personaje(name = newTextValue)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Nombre del personaje",
                keyboardType = KeyboardType.Text,
            )
            MyOutlinedTextField(
                textValue = viewModel.personaje.collectAsState().value.description,
                onValueChange = { newTextValue -> viewModel.setPersonaje(Personaje(description = newTextValue)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
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
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.agility.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(agility = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La agilidad necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Agilidad",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.constitution.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(constitution = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La constitución necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Constitución",
                    modifier = Modifier
                        .width(150.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.dexterity.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(dexterity = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La destreza necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Destreza",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.strength.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(strength = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La fuerza necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Fuerza",
                    modifier = Modifier
                        .width(150.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.intelligence.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(intelligence = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La inteligencia necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Inteligencia",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.perception.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(perception = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La percepción necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Percepción",
                    modifier = Modifier
                        .width(150.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.power.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(power = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El poder necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Poder",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = viewModel.personaje.collectAsState().value.will.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(will = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La voluntad necesita ser un número"))
                            }
                        }
                    },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Voluntad",
                    modifier = Modifier
                        .width(150.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.level.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(level = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El nivel necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Nivel",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.attackHability.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(attackHability = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La habilidad de ataque necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Hab. de ataque",
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
                    textValue = viewModel.personaje.collectAsState().value.dodge.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(dodge = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La esquiva necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Esquiva",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.parryHability.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(parryHability = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La parada necesita ser un número"))
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
                    textValue = viewModel.personaje.collectAsState().value.armor.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(armor = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("Llevar armadura necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Llevar armadura",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.turn.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(turn = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El turno necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Turno",
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
                    textValue = viewModel.personaje.collectAsState().value.rf.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(rf = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La RF necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(100.dp),
                    label = "RF",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.rm.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(rm = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La RM necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(100.dp),
                    label = "RM",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.rp.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(rp = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La RP necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(100.dp),
                    label = "RP",
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
                    textValue = viewModel.personaje.collectAsState().value.totalHp.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(totalHp = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("La vida necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Vida",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextField(
                    textValue = viewModel.personaje.collectAsState().value.totalStamina.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                viewModel.setPersonaje(Personaje(totalStamina = newTextValue.toInt()))
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El cansancio necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Cansancios",
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
                    onClick = { guardarPersonajeYRegresar(viewModel) },
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

fun guardarPersonajeYRegresar(
    viewModel: AddPersonajeVM
) {
    if (viewModel.personaje.value.name.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El nombre no puede estar vacío"))
    } else if (viewModel.personaje.value.clase.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("Selecciona una clase válida"))
    } else {
        viewModel.handleEvent(AddPersonajeContract.Event.AddPersonaje(viewModel.personaje.value))
    }
}
