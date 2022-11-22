package ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje

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

            val personajeEditando by remember { mutableStateOf(Personaje()) }

            var clasePersonaje by remember { mutableStateOf("") }
            var nombrePersonaje by remember { mutableStateOf("") }
            var descripcionPersonaje by remember { mutableStateOf("") }
            var nivelPersonaje by remember { mutableStateOf("") }
            var vidaPersonaje by remember { mutableStateOf("") }
            var staminaPersonaje by remember { mutableStateOf("") }

            MyOutlinedTextFieldWithDropDownMenu(
                textValue = clasePersonaje,
                onValueChange = { newTextValue ->
                    run {
                        clasePersonaje = newTextValue
                        personajeEditando.clase = clasePersonaje
                    }
                },
                list = Constantes.getClases(),
                color = color,
                label = "Clase del personaje",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )
            MyOutlinedTextField(
                textValue = nombrePersonaje,
                onValueChange = { newTextValue ->
                    run {
                        nombrePersonaje = newTextValue
                        personajeEditando.name = nombrePersonaje
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Nombre del personaje",
                keyboardType = KeyboardType.Text,
            )
            MyOutlinedTextField(
                textValue = descripcionPersonaje,
                onValueChange = { newTextValue ->
                    run {
                        descripcionPersonaje = newTextValue
                        personajeEditando.description = descripcionPersonaje
                    }
                },
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
                MyOutlinedTextField(
                    textValue = nivelPersonaje,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                nivelPersonaje = newTextValue
                                personajeEditando.level = nivelPersonaje.toInt()
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextField(
                    textValue = vidaPersonaje,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                vidaPersonaje = newTextValue
                                personajeEditando.totalHp = vidaPersonaje.toInt()
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
                    textValue = staminaPersonaje,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                staminaPersonaje = newTextValue
                                personajeEditando.totalStamina = staminaPersonaje.toInt()
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = personajeEditando.agility.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.agility = newTextValue.toInt()
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
                    textValue = personajeEditando.constitution.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.constitution = newTextValue.toInt()
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
                    textValue = personajeEditando.dexterity.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.dexterity = newTextValue.toInt()
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
                    textValue = personajeEditando.strength.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.strength = newTextValue.toInt()
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
                    textValue = personajeEditando.intelligence.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.intelligence = newTextValue.toInt()
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
                    textValue = personajeEditando.perception.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.perception = newTextValue.toInt()
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
                    textValue = personajeEditando.power.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.power = newTextValue.toInt()
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
                    textValue = personajeEditando.will.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                personajeEditando.will = newTextValue.toInt()
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
                        guardarPersonajeYRegresar(
                            viewModel = viewModel,
                            onBackPressed = onBackPressed,
                            personajeEditando = personajeEditando
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

fun guardarPersonajeYRegresar(
    viewModel: AddPersonajeVM,
    onBackPressed: () -> Unit,
    personajeEditando: Personaje
) {
    if (personajeEditando.clase.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("Selecciona una clase válida"))
    } else if (personajeEditando.name.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El nombre no puede estar vacío"))
    } else {
        viewModel.handleEvent(AddPersonajeContract.Event.AddPersonaje(personajeEditando))
        onBackPressed()
    }
}
