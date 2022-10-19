package ies.quevedo.rpgchardatcompose.framework.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    keyboardType: KeyboardType? = null,
    textValue: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textValue,
        onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(fontSize = 15.sp),
        modifier = modifier,
        label = { Text(text = label, color = Color.White) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ?: KeyboardType.Text,
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
        )
    )
}