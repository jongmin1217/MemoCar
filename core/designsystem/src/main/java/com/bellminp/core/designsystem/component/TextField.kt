package com.bellminp.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp
import java.text.DecimalFormat

@Composable
fun LabelTextField(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    keyboardType: KeyboardType,
    isDecimal: Boolean,
    onChangeText: (String) -> Unit,
) {

    OutlinedTextField(
        value = text,
        onValueChange = {
            onChangeText(
                if (isDecimal) {
                    val df = DecimalFormat("#,###")
                    df.format(it.replace(",","").toLong())
                } else it
            )
        },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(
            fontSize = 16.dp.textSp,
            lineHeight = 24.dp.textSp,
            fontWeight = FontWeight.W500,
            color = Color.Black
        ),
        label = {
            Text(
                text = labelText,
                style = TextStyle(
                    fontWeight = FontWeight.W400
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        )
    )
}