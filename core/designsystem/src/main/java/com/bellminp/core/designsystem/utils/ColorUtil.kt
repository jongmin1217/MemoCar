package com.bellminp.core.designsystem.utils

import androidx.compose.material3.ButtonColors
import androidx.compose.ui.graphics.Color

fun transparentButtonColor() = ButtonColors(
    containerColor = Color.Transparent,
    contentColor = Color.Transparent,
    disabledContentColor = Color.Transparent,
    disabledContainerColor = Color.Transparent
)

fun String.color() = Color(android.graphics.Color.parseColor("#${this.substring(2,8)}"))