package com.bellminp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Setting
import kotlin.math.ceil

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VerticalCheckList(
    modifier: Modifier = Modifier,
    title: String,
    selectId: Long?,
    list: List<Setting>,
    onCLick: (Long?) -> Unit,
) {

    if(selectId != null && list.find { it.id == selectId } == null) onCLick(null)

    Column(
        modifier = modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 18.dp.textSp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(start = 10.dp, bottom = 15.dp)
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .height((ceil(list.size.toFloat()/3) * 50).dp)
                .background(Color.White)
        ) {
            selectCardList(
                items = list,
                selectId = selectId,
                onCLick = onCLick
            )
        }
    }
}