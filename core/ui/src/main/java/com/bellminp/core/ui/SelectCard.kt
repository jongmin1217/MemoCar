package com.bellminp.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.SelectBoxData
import com.bellminp.core.model.data.Setting

@Composable
fun SelectCard(
    item: SelectBoxData,
    selectId: Long?,
    modifier: Modifier = Modifier,
    onCLick: (Long) -> Unit,
) {
    val isSelect = item.id == selectId

    Surface(
        modifier
            .height(50.dp),
        color = Color.White
    ) {
        Box(
            modifier = modifier
                .padding(5.dp)
                .border(
                    width = if (isSelect) 2.dp else 1.dp,
                    color = if (isSelect) MaterialTheme.colorScheme.primary else Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { onCLick(item.id) }
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                if(item.isShowImage){
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = "brandImage",
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.CenterVertically),
                        placeholder = painterResource(id = R.drawable.img_camera),
                        fallback = painterResource(id = R.drawable.img_camera),
                        error = painterResource(id = R.drawable.img_camera)
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                }

                Text(
                    text = item.name,
                    style = TextStyle(
                        fontSize = 13.dp.textSp,
                        fontWeight = if (isSelect) FontWeight.W800 else FontWeight.W500,
                        color = if (isSelect) MaterialTheme.colorScheme.primary else Color.Gray
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

        }
    }

}