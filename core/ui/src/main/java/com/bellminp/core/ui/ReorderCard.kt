package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bellminp.core.designsystem.component.SwipeContent
import com.bellminp.core.designsystem.component.SwipeContentType
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.Type
import com.kevinnzou.compose.swipebox.AnchoredDragBox
import com.kevinnzou.compose.swipebox.DragAnchors
import com.kevinnzou.compose.swipebox.SwipeDirection
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReorderCard(
    modifier: Modifier = Modifier,
    item: Item,
    isDragging: Boolean,
    onDeleteClick: (Item) -> Unit,
    onClick: (Item) -> Unit,
) {

    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .scale(if (isDragging) 1.1f else 1f)
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
            .shadow(
                if (isDragging) 16.dp else 3.dp,
                shape = RoundedCornerShape(8.dp),
                spotColor = Color(0x4A000000)
            )
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        AnchoredDragBox(
            modifier = Modifier.fillMaxSize(),
            swipeDirection = SwipeDirection.EndToStart,
            endContentWidth = 80.dp,
            endContent = { anchoredDraggableState, _ ->
                Row {
                    SwipeContent(
                        type = SwipeContentType.DELETE,
                        onClick = {
                            scope.launch {
                                anchoredDraggableState.animateTo(DragAnchors.Center)
                                onDeleteClick(item)
                            }
                        }
                    )
                }
            }
        ) { _, _, _ ->
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 16.dp.textSp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                )

                Text(
                    text = when (item.inputType.type) {
                        Type.WRITE -> Type.WRITE.text
                        Type.SELECT -> Type.SELECT.text
                        Type.IMAGE -> Type.IMAGE.text
                    },
                    style = TextStyle(
                        fontSize = 16.dp.textSp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}