package ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto

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
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.framework.common.MyOutlinedTextField

@Composable
fun ShowObjetoContent(
    modifier: Modifier,
    color: Animatable<Color, AnimationVector4D>,
    viewModel: ShowObjetoVM,
    objetoParaActualizar: Objeto,
    onBackPressed: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(color.value)
    ) {
        item {
            val objetoEditando by remember { mutableStateOf(objetoParaActualizar) }

            val nombreObjetoInit = objetoParaActualizar.name
            val descripcionObjetoInit = objetoParaActualizar.description
            val valorObjetoInit = objetoParaActualizar.value
            val cantidadObjetoInit = objetoParaActualizar.amount
            var nombreObjeto by remember { mutableStateOf(nombreObjetoInit) }
            var descripcionObjeto by remember { mutableStateOf(descripcionObjetoInit) }
            var valorObjeto by remember { mutableStateOf(valorObjetoInit.toString()) }
            var cantidadObjeto by remember { mutableStateOf(cantidadObjetoInit.toString()) }

            MyOutlinedTextField(
                textValue = nombreObjeto,
                onValueChange = { newTextValue -> nombreObjeto = newTextValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 50.dp),
                label = "Objeto",
                keyboardType = KeyboardType.Text
            )

            MyOutlinedTextField(
                textValue = descripcionObjeto,
                onValueChange = { newValue -> descripcionObjeto = newValue },
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
                    modifier = Modifier
                        .width(150.dp),
                    label = "Valor",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.weight(2f))
                MyOutlinedTextField(
                    textValue = cantidadObjeto,
                    onValueChange = { newTextValue -> cantidadObjeto = newTextValue },
                    modifier = Modifier
                        .width(150.dp),
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
                    modifier = Modifier
                        .width(120.dp),
                    onClick = {
                        try {
                            objetoEditando.name = nombreObjeto
                            objetoEditando.description = descripcionObjeto
                            objetoEditando.value = valorObjeto.toInt()
                            objetoEditando.amount = cantidadObjeto.toInt()
                            actualizarObjetoYRegresar(
                                viewModel = viewModel,
                                onBackPressed = onBackPressed,
                                objetoEditando = objetoEditando
                            )
                        } catch (e: NumberFormatException) {
                            viewModel.handleEvent(
                                ShowObjetoContract.Event.ShowError("Los campos numéricos no pueden contener letras")
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

fun actualizarObjetoYRegresar(
    viewModel: ShowObjetoVM,
    onBackPressed: () -> Unit,
    objetoEditando: Objeto
) {
    if (objetoEditando.name.isEmpty()) {
        viewModel.handleEvent(ShowObjetoContract.Event.ShowError("Introduce un objeto"))
    } else {
        viewModel.handleEvent(ShowObjetoContract.Event.UpdateObjeto(objeto = objetoEditando))
        onBackPressed()
    }
}
