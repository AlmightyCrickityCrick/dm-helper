package com.example.dmhelper.presentation.session.editor

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.data.session.CreateSessionDTO
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.components.board.EditorBoard
import com.example.dmhelper.presentation.components.board.ItemBoard
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.presentation.components.button.RoundButtonSizes
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun SessionEditorScreen(
    navController: NavHostController,
    viewModel: SessionEditorViewModel = koinViewModel(),
    createSessionDTO: CreateSessionDTO
) {
    val image = when (createSessionDTO.map) {
        R.drawable.map_garden.toString() -> R.drawable.map_garden
        R.drawable.map_desert_tomb.toString() -> R.drawable.map_desert_tomb
        else -> R.drawable.map_garden
    }
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop),
    ) { _ ->
        MapEditorScreen(
            mapPainter = painterResource(image),
            availableObjects = listOf(
                MapObjectTemplate("rogue", R.drawable.ic_rogue), MapObjectTemplate("fighter", R.drawable.ic_fighter)
            ),
            gridSize = 64,
            onBackPressed = { navController.popBackStack() },
        )
    }
}

@Composable
fun MapEditorScreen(
    mapPainter: Painter,
    availableObjects: List<MapObjectTemplate>,
    gridSize: Int = 64,
    onBackPressed: () -> Unit
) {
    var selectedTemplate by remember { mutableStateOf<MapObjectTemplate?>(null) }
    var placedObjects by remember { mutableStateOf<List<MapObject>>(emptyList()) }
    var selectedObject by remember { mutableStateOf<MapObject?>(null) }
    var isLive by rememberSaveable { mutableStateOf<Boolean>(false) }
    var name by remember { mutableStateOf<String>("Objects") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(selectedTemplate) {
                detectTapGestures { offset ->
                    selectedTemplate?.let { template ->
                        val snappedX = (offset.x / gridSize).toInt()
                        val snappedY = (offset.y / gridSize).toInt()

                        val newObj = MapObject(
                            name = template.name,
                            drawableRes = template.drawableRes,
                            gridX = snappedX,
                            gridY = snappedY
                        )
                        placedObjects = placedObjects + newObj
                    }
                }
            }
    ) {
        Image(
            painter = mapPainter,
            contentDescription = "Map",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        placedObjects.forEach { obj ->
            Image(
                painter = painterResource(id = obj.drawableRes),
                contentDescription = obj.name,
                modifier = Modifier
                    .size(gridSize.dp)
                    .offset {
                        IntOffset(obj.gridX * gridSize, obj.gridY * gridSize)
                    }
                    .clickable {
                        selectedObject = obj
                        selectedTemplate = null
                    }
            )
        }

        if (!isLive) Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp)
        ) {
            ItemBoard(
                text = "NPC", modifier = Modifier
                    .width(150.dp)
                    .height(80.dp), onClick = { name = "NPC" }, leftTextPadding = 10
            )
            Spacer(Modifier.height(20.dp))
            ItemBoard(
                text = "Object", modifier = Modifier
                    .width(150.dp)
                    .height(80.dp), onClick = { name = "Objects" }, leftTextPadding = 10
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp, top = 10.dp)
        ) {
            RoundButton(size = RoundButtonSizes.S, onClick = { isLive = !isLive }, text = "", icon = R.drawable.ic_live)
            Spacer(Modifier.width(20.dp))
            RoundButton(size = RoundButtonSizes.S, onClick = {onBackPressed.invoke()}, text = "", icon = R.drawable.ic_save)
        }

        selectedObject?.let {
            EditorBoard(objectState = it, onEvent = {}, modifier = Modifier.align(Alignment.BottomCenter))
        }

        if (!isLive) LeftDrawer(
            modifier = Modifier.align(Alignment.TopStart),
            name = name,
            availableObjects = availableObjects,
            selectedTemplate = selectedTemplate,
            onNewTemplateSelected = { temp -> selectedTemplate = temp })
    }
}

@Composable
fun LeftDrawer(
    modifier: Modifier,
    name: String,
    availableObjects: List<MapObjectTemplate>,
    selectedTemplate: MapObjectTemplate?,
    onNewTemplateSelected: (MapObjectTemplate) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(200.dp)
            .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.FillBounds)
            .padding(horizontal = 8.dp, vertical = 20.dp)
    ) {
        Text(
            text = name, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(availableObjects.size) { index ->
                val template = availableObjects[index]
                Image(
                    painter = painterResource(id = template.drawableRes),
                    contentDescription = template.name,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(4.dp)
                        .border(
                            2.dp,
                            if (selectedTemplate == template) Color.Green else Color.Transparent
                        )
                        .clickable { onNewTemplateSelected.invoke(template) }
                )
            }
        }
    }
}


//@OrientationPreview
//@Composable
//fun PreviewSessionEditorScreen() {
//    DMHelperTheme {
//        SessionEditorScreen(rememberNavController(), SessionEditorViewModel(), )
//    }
//}

