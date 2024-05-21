package com.bellminp.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bellminp.core.designsystem.utils.textSp
import com.bellminp.core.model.data.Category
import com.kevinnzou.compose.swipebox.AnchoredDragBox
import com.kevinnzou.compose.swipebox.DragAnchors
import com.kevinnzou.compose.swipebox.SwipeDirection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryCard(
    category: Category,
    isKeyboardOpen : Boolean,
    isDragging : Boolean,
    modifier: Modifier = Modifier,
    onDeleteClick: (Category) -> Unit,
    onNameChange : (Category) -> Unit
) {
    val scope = rememberCoroutineScope()
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    var categoryText by remember {
        mutableStateOf(
            TextFieldValue(
                text = category.name,
                selection = TextRange(category.name.length)
            )
        )
    }

    var isEnableEdit by remember {
        mutableStateOf(false)
    }

    var isFocus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (isKeyboardOpen.not()) {
            isEnableEdit = false
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
                elevation = if(isDragging) 16.dp else 3.dp,
                shape = RoundedCornerShape(8.dp),
                spotColor = Color(0x4A000000)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = cardColors(
            containerColor = Color.White
        )
    ) {
        AnchoredDragBox(
            modifier = Modifier.fillMaxSize(),
            swipeDirection = SwipeDirection.EndToStart,
            endContentWidth = 120.dp,
            endContent = { anchoredDraggableState, _ ->
                Row {
                    repeat(2){ index ->
                        SwipeContent(
                            scope = scope,
                            anchoredDraggableState = anchoredDraggableState,
                            type = if(index == 0) SwipeContentType.EDIT else SwipeContentType.DELETE,
                            onClick = {
                                if(it == SwipeContentType.EDIT){
                                    scope.launch {
                                        isEnableEdit = true
                                        delay(100)
                                        focusRequester.requestFocus()
                                    }
                                }else{
                                    onDeleteClick(category)
                                }
                            }
                        )
                    }
                }
            }
        ){_, _, _ ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                BasicTextField(
                    value = categoryText,
                    onValueChange = {
                        if(it.text.length <= 10) {
                            categoryText = it
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 18.dp.textSp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if(isFocus && it.hasFocus.not()) onNameChange(category.copy(name = categoryText.text))
                            isFocus = it.hasFocus
                        },
                    enabled = isEnableEdit,
                    singleLine = true
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeContent(
    scope : CoroutineScope,
    anchoredDraggableState : AnchoredDraggableState<DragAnchors>,
    type : SwipeContentType,
    onClick : (SwipeContentType) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(60.dp)
            .clickable {
                scope.launch {
                    anchoredDraggableState.animateTo(DragAnchors.Center)
                    onClick(type)
                }
            }
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