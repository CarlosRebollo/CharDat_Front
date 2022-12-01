package ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto

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
import androidx.navigation.NavHostController
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField
import ies.quevedo.rpgchardatcompose.framework.navigation.Screen
import java.util.*

@Composable
fun AddObjetoContent(
    idPersonaje: Int?,
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: AddObjetoVM,
    navController: NavHostController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val objetoEditando by remember { mutableStateOf(Objeto()) }

            var nombreObjeto by remember { mutableStateOf("") }
            var descripcionObjeto by remember { mutableStateOf("") }
            var valorObjeto by remember { mutableStateOf("") }
            var cantidadObjeto by remember { mutableStateOf("") }

            MyOutlinedTextField(
                textValue = nombreObjeto,
                onValueChange = { newTextValue -> nombreObjeto = newTextValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Nombre",
                keyboardType = KeyboardType.Text
            )

            MyOutlinedTextField(
                textValue = descripcionObjeto,
                onValueChange = { newTextValue -> descripcionObjeto = newTextValue },
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
                    textValue = valorObjeto,
                    onValueChange = { newTextValue -> valorObjeto = newTextValue },
                    modifier = Modifier.width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = cantidadObjeto,
                    onValueChange = { newTextValue -> cantidadObjeto = newTextValue },
                    modifier = Modifier.width(150.dp),
                    label = "Cantidad",
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
                            if (valorObjeto == "") valorObjeto = "0"
                            if (cantidadObjeto == "") cantidadObjeto = "0"
                            objetoEditando.name = nombreObjeto.uppercase(Locale.ROOT)
                            objetoEditando.description = descripcionObjeto
                            objetoEditando.value = valorObjeto.toInt()
                            objetoEditando.amount = cantidadObjeto.toInt()
                            guardarObjetoYRegresar(
                                idPersonaje = idPersonaje,
                                objetoEditando = objetoEditando,
                                viewModel = viewModel,
                                navController = navController
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                AddObjetoContract.Event.ShowError("Los campos numéricos no pueden contener letras")
                            )
                        }
                    },
                ) {
                    Text(text = "AÑADIR", fontSize = 16.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier.width(120.dp),
                    onClick = { navController.popBackStack() }) {
                    Text(text = "CANCELAR", fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun guardarObjetoYRegresar(
    idPersonaje: Int?,
    objetoEditando: Objeto,
    viewModel: AddObjetoVM,
    navController: NavHostController
) {
    if (objetoEditando.name.isEmpty()) {
        viewModel.handleEvent(AddObjetoContract.Event.ShowError("Introduce un objeto"))
    } else {
        if (idPersonaje != null) {
            objetoEditando.idPJ = idPersonaje
            viewModel.handleEvent(AddObjetoContract.Event.AddObjeto(objeto = objetoEditando))
            navController.navigate(Screen.ListaObjetos.mandarIdPersonaje(idPersonaje)) {
                popUpTo(Screen.ListaObjetos.route) {
                    inclusive = true
                }
            }
        } else {
            viewModel.handleEvent(AddObjetoContract.Event.ShowError("Error obteniendo los datos del personaje"))
        }
    }
}
