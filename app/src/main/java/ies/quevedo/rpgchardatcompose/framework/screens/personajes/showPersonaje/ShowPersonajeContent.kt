package ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje

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
fun ShowPersonajeContent(
    personajeParaActualizar: Personaje,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: ShowPersonajeVM,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val personajeEditando by remember { mutableStateOf(personajeParaActualizar) }

            var clasePersonaje by remember { mutableStateOf(personajeEditando.clase) }
            var nombrePersonaje by remember { mutableStateOf(personajeEditando.name) }
            var descripcionPersonaje by remember { mutableStateOf(personajeEditando.description) }
            var nivelPersonaje by remember { mutableStateOf(personajeEditando.level) }
            var vidaPersonaje by remember { mutableStateOf(personajeEditando.totalHp) }
            var staminaPersonaje by remember { mutableStateOf(personajeEditando.totalStamina) }
            var agilidad by remember { mutableStateOf(personajeEditando.agility) }
            var constitucion by remember { mutableStateOf(personajeEditando.constitution) }
            var destreza by remember { mutableStateOf(personajeEditando.dexterity) }
            var fuerza by remember { mutableStateOf(personajeEditando.strength) }
            var inteligencia by remember { mutableStateOf(personajeEditando.intelligence) }
            var percepcion by remember { mutableStateOf(personajeEditando.perception) }
            var poder by remember { mutableStateOf(personajeEditando.power) }
            var voluntad by remember { mutableStateOf(personajeEditando.will) }

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
                    textValue = nivelPersonaje.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                nivelPersonaje = newTextValue.toInt()
                                personajeEditando.level = nivelPersonaje
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("El nivel necesita ser un número"))
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
                    textValue = vidaPersonaje.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                vidaPersonaje = newTextValue.toInt()
                                personajeEditando.totalHp = vidaPersonaje
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La vida necesita ser un número"))
                            }
                        }
                    },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Vida",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = staminaPersonaje.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                staminaPersonaje = newTextValue.toInt()
                                personajeEditando.totalStamina = staminaPersonaje
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("El cansancio necesita ser un número"))
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
                    textValue = agilidad.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                agilidad = newTextValue.toInt()
                                personajeEditando.agility = agilidad
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La agilidad necesita ser un número"))
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
                    textValue = constitucion.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                constitucion = newTextValue.toInt()
                                personajeEditando.constitution = constitucion
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La constitución necesita ser un número"))
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
                    textValue = destreza.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                destreza = newTextValue.toInt()
                                personajeEditando.dexterity = destreza
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La destreza necesita ser un número"))
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
                    textValue = fuerza.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                fuerza = newTextValue.toInt()
                                personajeEditando.strength = fuerza
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La fuerza necesita ser un número"))
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
                    textValue = inteligencia.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                inteligencia = newTextValue.toInt()
                                personajeEditando.intelligence = inteligencia
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La inteligencia necesita ser un número"))
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
                    textValue = percepcion.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                percepcion = newTextValue.toInt()
                                personajeEditando.perception = percepcion
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La percepción necesita ser un número"))
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
                    textValue = poder.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                poder = newTextValue.toInt()
                                personajeEditando.power = poder
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("El poder necesita ser un número"))
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
                    textValue = voluntad.toString(),
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                voluntad = newTextValue.toInt()
                                personajeEditando.will = voluntad
                            } catch (ex: NumberFormatException) {
                                viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("La voluntad necesita ser un número"))
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
                        .width(140.dp),
                    onClick = {
                        actualizarPersonajeYRegresar(
                            viewModel = viewModel,
                            onBackPressed = onBackPressed,
                            personajeEditando = personajeEditando
                        )
                    },
                ) {
                    Text(text = "ACTUALIZAR", fontSize = 16.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier
                        .width(140.dp),
                    onClick = { onBackPressed() }
                ) {
                    Text(text = "CANCELAR", fontSize = 16.sp, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun actualizarPersonajeYRegresar(
    viewModel: ShowPersonajeVM,
    onBackPressed: () -> Unit,
    personajeEditando: Personaje
) {
    if (personajeEditando.clase.isEmpty()) {
        viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("Selecciona una clase válida"))
    } else if (personajeEditando.name.isEmpty()) {
        viewModel.handleEvent(ShowPersonajeContract.Event.ShowError("El nombre no puede estar vacío"))
    } else {
        viewModel.handleEvent(ShowPersonajeContract.Event.UpdatePersonaje(personajeEditando))
        onBackPressed()
    }
}