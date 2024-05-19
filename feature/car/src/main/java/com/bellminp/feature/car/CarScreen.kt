package com.bellminp.feature.car

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CarRoute(
    onBackClick : () -> Unit,
    onSaveClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(text = "this screen is car", modifier = Modifier.clickable { onSaveClick.invoke() })
        Text(text = "back", modifier = Modifier.padding(top = 100.dp).clickable { onBackClick.invoke() })
    }
}