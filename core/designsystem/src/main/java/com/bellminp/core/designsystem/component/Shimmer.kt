package com.bellminp.core.designsystem.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerComponent(
    width : Dp? = null,
    height : Dp? = null,
    radius : Dp = 0.dp,
    @SuppressLint("ModifierParameter") modifier : Modifier? = null
){

    val newModifier = Modifier
        .then(modifier ?: run { Modifier })
        .then(width?.let { Modifier.width(it) }?: run { Modifier.fillMaxWidth() })
        .then(height?.let { Modifier.height(it) }?: run { Modifier.fillMaxHeight() })
        .then(Modifier.clip(RoundedCornerShape(radius)))
        .then(Modifier.background(shimmerBrush()))

    Box(modifier = newModifier)
}

@Composable
fun shimmerBrush(targetValue : Float = 1500f) : Brush {
    val shimmerColors = listOf(
        Color(0xffE6E6E6).copy(alpha = 1.0f),
        Color(0xffE6E6E6).copy(alpha = 0.5f),
        Color(0xffE6E6E6).copy(alpha = 1.0f),
        Color(0xffE6E6E6).copy(alpha = 0.5f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(800), repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}