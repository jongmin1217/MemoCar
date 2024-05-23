package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateTo
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bellminp.core.designsystem.component.SwipeContent
import com.bellminp.core.designsystem.component.SwipeContentType
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.SwipeItem
import com.kevinnzou.compose.swipebox.AnchoredDragBox
import com.kevinnzou.compose.swipebox.DragAnchors
import com.kevinnzou.compose.swipebox.SwipeDirection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeCard(
    item: SwipeItem,
    isKeyboardOpen : Boolean,
    isDragging : Boolean,
    modifier: Modifier = Modifier,
    onDeleteClick: (SwipeItem) -> Unit,
    onNameChange : (SwipeItem, String) -> Unit
){
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    var brandText by remember {
        mutableStateOf(
            TextFieldValue(
                text = item.getItemName(),
                selection = TextRange(item.getItemName().length)
            )
        )
    }

    var isFocus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (isKeyboardOpen.not()) {
            focusManager.clearFocus()
        }
    }

    Card(
        modifier = modifier
            .scale(if(isDragging) 1.1f else 1f)
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
            .shadow(
                if(isDragging) 16.dp else 3.dp,
                shape = RoundedCornerShape(8.dp),
                spotColor = Color(0x4A000000)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        AnchoredDragBox(
            modifier = Modifier.fillMaxSize(),
            swipeDirection = SwipeDirection.EndToStart,
            endContentWidth = 60.dp,
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
        ){_, _, _ ->
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                if(item.getImageState().isShow){
                    Spacer(modifier = Modifier.width(5.dp))

                    AsyncImage(
                        model = item.getImageState().imageUrl,
                        contentDescription = "brandImage",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .align(Alignment.CenterVertically),
                        placeholder = painterResource(id = R.drawable.img_camera),
                        fallback = painterResource(id = R.drawable.img_camera),
                        error = painterResource(id = R.drawable.img_camera)
                    )
                }


                BasicTextField(
                    value = brandText,
                    onValueChange = {
                        if(it.text.length <= 10) {
                            brandText = it
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 18.dp.textSp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (isFocus && it.hasFocus.not()) onNameChange(item,brandText.text)
                            isFocus = it.hasFocus
                        },
                    singleLine = true
                )
            }
        }
    }
}