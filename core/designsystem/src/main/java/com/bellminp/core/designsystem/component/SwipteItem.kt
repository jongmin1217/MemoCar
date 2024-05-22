package com.bellminp.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwipeContent(
    type : SwipeContentType,
    onClick : (SwipeContentType) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(60.dp)
            .clickable { onClick(type) }
            .background(
                if(type == SwipeContentType.EDIT) MaterialTheme.colorScheme.primaryContainer
                else Color.Red
            )
    ){
        Icon(
            imageVector = if(type == SwipeContentType.EDIT) Icons.Rounded.Edit else Icons.Rounded.Delete,
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

enum class SwipeContentType{
    EDIT,
    DELETE
}