package com.example.dmhelper.presentation.components.board

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksharing.presentation.components.input.SimpleInput
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.session.editor.MapObject
import com.example.dmhelper.presentation.session.editor.SessionEditorUiEvent
import com.example.dmhelper.ui.theme.DMHelperTheme


@Composable
fun EditorBoard(objectState: MapObject, onEvent: (SessionEditorUiEvent) -> Unit, modifier: Modifier = Modifier) {
    val (wFactor, hFactor) = getFactors()
    Column(
        modifier = modifier
            .width((400 * wFactor).dp)
            .height(((105 * wFactor) + 40).dp)
            .padding(top = 24.dp, start = 24.dp)
    ) {
        // Wooden board
        Box(
            modifier = Modifier
                .height((100 * wFactor).dp)
                .width((400 * wFactor).dp)
                .clip(RoundedCornerShape(4.dp))
                .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
                .border(2.dp, MaterialTheme.colorScheme.onBackground),
        ) {
            Text(
                text = objectState.name,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 12.dp)
            )
            Icon(
                painterResource(R.drawable.ic_remove),
                tint = MaterialTheme.colorScheme.secondaryContainer,
                contentDescription = "Remove item",
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    ).size(40.dp).padding(top = 10.dp, end=12.dp)
                    .clickable(onClick = { onEvent(SessionEditorUiEvent.RemoveObject(objectState.id)) })
            )
            Column(
                Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(0.45f)
                    .fillMaxHeight()
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.ic_active),
                        contentDescription = "Is Visible",
                        tint = if (objectState.visible) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surfaceContainer,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = {})
                    )
                    Text(
                        text = "Visible",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.ic_active),
                        contentDescription = "Is Visible",
                        tint = if (objectState.visible) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surfaceContainer,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = {})
                    )
                    Text(
                        text = "Discover",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.45f)
                    .fillMaxHeight()
                    .padding(start = 12.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.ic_active),
                        contentDescription = "Is Visible",
                        tint = if (objectState.visible) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surfaceContainer,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = {})
                    )
                    Text(
                        text = "Pick Up",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.ic_active),
                        contentDescription = "Is Visible",
                        tint = if (objectState.visible) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.surfaceContainer,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = {})
                    )
                    Text(
                        text = "DC",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .width((400 * wFactor).dp)
                .padding(horizontal = 30.dp)
        ) {
            Box(
                Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Box(
                Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditorBoard() {
    DMHelperTheme {
        EditorBoard(
            MapObject(
                name = "Chest", gridX = 20, gridY = 20,
                drawableRes = R.drawable.ic_rogue,
            ), {})
    }
}