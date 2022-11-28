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
import ies.quevedo.rpgchardatcompose.framework.utils.Constantes
import java.time.LocalDate

@Composable
fun AddPersonajeContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddPersonajeVM,
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
                onValueChange = { newTextValue -> clasePersonaje = newTextValue },
                list = Constantes.getClases(),
                color = color,
                label = "Clase del personaje",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp)
            )

            MyOutlinedTextField(
                textValue = nombrePersonaje,
                onValueChange = { newTextValue -> nombrePersonaje = newTextValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Nombre del personaje",
                keyboardType = KeyboardType.Text,
            )

            MyOutlinedTextField(
                textValue = descripcionPersonaje,
                onValueChange = { newTextValue -> descripcionPersonaje = newTextValue },
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
                    onValueChange = { newTextValue -> nivelPersonaje = newTextValue },
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
                    onValueChange = { newTextValue -> vidaPersonaje = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
                    label = "Vida",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = staminaPersonaje,
                    onValueChange = { newTextValue -> staminaPersonaje = newTextValue },
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
                    onValueChange = { newTextValue -> agilidad = newTextValue },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Agilidad",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = constitucion,
                    onValueChange = { newTextValue -> constitucion = newTextValue },
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
                    onValueChange = { newTextValue -> destreza = newTextValue },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Destreza",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = fuerza,
                    onValueChange = { newTextValue -> fuerza = newTextValue },
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
                    onValueChange = { newTextValue -> inteligencia = newTextValue },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Inteligencia",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = percepcion,
                    onValueChange = { newTextValue -> percepcion = newTextValue },
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
                    onValueChange = { newTextValue -> poder = newTextValue },
                    list = Constantes.getStats(),
                    color = color,
                    label = "Poder",
                    modifier = Modifier
                        .width(150.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                MyOutlinedTextFieldWithDropDownMenu(
                    textValue = voluntad,
                    onValueChange = { newTextValue -> voluntad = newTextValue },
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
                        try {
                            if (nivelPersonaje == "") nivelPersonaje = "0"
                            if (vidaPersonaje == "") vidaPersonaje = "0"
                            if (staminaPersonaje == "") staminaPersonaje = "0"
                            if (agilidad == "") agilidad = "0"
                            if (constitucion == "") constitucion = "0"
                            if (destreza == "") destreza = "0"
                            if (fuerza == "") fuerza = "0"
                            if (inteligencia == "") inteligencia = "0"
                            if (percepcion == "") percepcion = "0"
                            if (poder == "") poder = "0"
                            if (voluntad == "") voluntad = "0"
                            personajeEditando.clase = clasePersonaje
                            personajeEditando.name = nombrePersonaje
                            personajeEditando.description = descripcionPersonaje
                            personajeEditando.level = nivelPersonaje.toInt()
                            personajeEditando.totalHp = vidaPersonaje.toInt()
                            personajeEditando.totalStamina = staminaPersonaje.toInt()
                            personajeEditando.agility = agilidad.toInt()
                            personajeEditando.constitution = constitucion.toInt()
                            personajeEditando.dexterity = destreza.toInt()
                            personajeEditando.strength = fuerza.toInt()
                            personajeEditando.intelligence = inteligencia.toInt()
                            personajeEditando.perception = percepcion.toInt()
                            personajeEditando.power = poder.toInt()
                            personajeEditando.will = voluntad.toInt()
                            personajeEditando.creationDate = LocalDate.now().toString()
                            guardarPersonajeYRegresar(
                                viewModel = viewModel,
                                personajeEditando = personajeEditando,
                                onBackPressed = onBackPressed
                            )
                        } catch (e: NumberFormatException) {
                            AddPersonajeContract.Event.ShowError("Los campos numéricos no pueden contener letras")
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

fun guardarPersonajeYRegresar(
    viewModel: AddPersonajeVM,
    personajeEditando: Personaje,
    onBackPressed: () -> Unit
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
