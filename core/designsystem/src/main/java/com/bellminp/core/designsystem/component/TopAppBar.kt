package com.bellminp.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = null,
    actionContent: @Composable (() -> Unit)? = null,
    onNavigationClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                style = TextStyle(
                    fontSize = 18.dp.textSp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        actions = {
            actionContent?.invoke()
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        modifier = modifier
            .testTag("HeaderScreen"),
    )
}