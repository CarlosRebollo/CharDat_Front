package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun MyOutlinedTextFieldWithDropDownMenu(
    list: Array<String>,
    color: Animatable<Color, AnimationVector4D>,
    label: String,
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    Column {
        OutlinedTextField(
            value = textValue,
            enabled = false,
            onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(fontSize = 15.sp),
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(text = label, color = Color.White) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.White
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color.value)
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onValueChange(label)
                }) {
                    Text(text = label, color = Color.White)
                }
            }
        }
    }
}