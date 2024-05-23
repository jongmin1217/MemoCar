package com.bellminp.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun DetailRoute(
    modifier: Modifier = Modifier
){
    DetailScreen()
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "detail", modifier = Modifier.align(Alignment.Center), style = TextStyle(color = Color.Black))
    }
}