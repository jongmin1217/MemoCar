package com.bellminp.core.designsystem.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.color
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val colorPickerController = ColorPickerController()
    var selectedColor by remember { mutableStateOf(Color.White) }


    Column(
        modifier = modifier
    ) {
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenWidth.dp),
            controller = colorPickerController,
            initialColor = Color(0xffffffff),
            onColorChanged = {

            }
        )


        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(vertical = 10.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(6.dp)
                ),
            controller = colorPickerController,
            initialColor = Color.White
        )


        AlphaTile(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp))
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    colorPickerController.selectByColor(
                        color = Color.Yellow,
                        fromUser = true
                    )
                },
            controller = colorPickerController
        )


    }
}