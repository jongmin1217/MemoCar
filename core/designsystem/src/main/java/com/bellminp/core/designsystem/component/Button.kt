package com.bellminp.core.designsystem.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.dashedBorder
import com.bellminp.core.designsystem.utils.transparentButtonColor

@Preview
@Composable
fun AddBtn(
    modifier: Modifier = Modifier,
    onClick : (() -> Unit) = {}
){
    Button(
        onClick = onClick,
        modifier = modifier
            .dashedBorder(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = transparentButtonColor()
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "",
            tint = Color.LightGray,
        )
    }
}