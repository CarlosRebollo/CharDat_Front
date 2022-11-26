package ies.quevedo.rpgchardatcompose.framework.screens.personajes.addPersonaje

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
import ies.quevedo.rpgchardatcompose.framework.navigation.Routes
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes

@Composable
fun AddPersonajeContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddPersonajeVM,
    onNavigate: (String) -> Unit,
    onBackPressed: () -> Unit
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
            var agilidad by remember { mutableStateOf("") }
            var constitucion by remember { mutableStateOf("") }
            var destreza by remember { mutableStateOf("") }
            var fuerza by remember { mutableStateOf("") }
            var inteligencia by remember { mutableStateOf("") }
            var percepcion by remember { mutableStateOf("") }
            var poder by remember { mutableStateOf("") }
            var voluntad by remember { mutableStateOf("") }

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
                Spacer(modifier = Modifier.weight(2f))
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
                    textValue = agilidad,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                agilidad = newTextValue
                                personajeEditando.agility = agilidad.toInt()
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
                    textValue = constitucion,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                constitucion = newTextValue
                                personajeEditando.constitution = constitucion.toInt()
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
                    textValue = destreza,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                destreza = newTextValue
                                personajeEditando.dexterity = destreza.toInt()
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
                    textValue = fuerza,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                fuerza = newTextValue
                                personajeEditando.strength = fuerza.toInt()
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
                    textValue = inteligencia,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                inteligencia = newTextValue
                                personajeEditando.intelligence = inteligencia.toInt()
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
                    textValue = percepcion,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                percepcion = newTextValue
                                personajeEditando.perception = percepcion.toInt()
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
                    textValue = poder,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                poder = newTextValue
                                personajeEditando.power = poder.toInt()
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
                    textValue = voluntad,
                    onValueChange = { newTextValue ->
                        run {
                            try {
                                voluntad = newTextValue
                                personajeEditando.will = voluntad.toInt()
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
                            personajeEditando = personajeEditando,
                            onNavigate = onNavigate
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
    personajeEditando: Personaje,
    onNavigate: (String) -> Unit
) {
    if (personajeEditando.clase.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("Selecciona una clase válida"))
    } else if (personajeEditando.name.isEmpty()) {
        viewModel.handleEvent(AddPersonajeContract.Event.ShowError("El nombre no puede estar vacío"))
    } else {
        viewModel.handleEvent(AddPersonajeContract.Event.AddPersonaje(personajeEditando))
        onNavigate(Routes.LISTA_PERSONAJES)
    }
}
