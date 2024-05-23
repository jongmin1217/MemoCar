package com.bellminp.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp
import java.text.DecimalFormat

@Composable
fun LabelTextField(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    labelText: String,
    keyboardType: KeyboardType,
    isDecimal: Boolean,
    descriptionText : String?,
    onChangeText: (TextFieldValue) -> Unit,
) {

    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                onChangeText(
                    if (isDecimal) {
                        if(it.text.isEmpty()) it
                        else {
                            val replaceText = DecimalFormat("#,###").format(it.text.replace(",","").toLong())
                            it.copy(
                                text = replaceText,
                                selection = TextRange(replaceText.length)
                            )
                        }
                    } else it
                )
            },
            modifier = Modifier
                .padding(end = if (descriptionText == null) 0.dp else 30.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 16.dp.textSp,
                lineHeight = 24.dp.textSp,
                fontWeight = FontWeight.W500,
                color = Color.Black,
                textAlign = if(descriptionText == null) TextAlign.Start else TextAlign.End
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
            ),

        )

        descriptionText?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 16.dp.textSp,
                    color = Color.Black,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 5.dp)
            )
        }
    }
}