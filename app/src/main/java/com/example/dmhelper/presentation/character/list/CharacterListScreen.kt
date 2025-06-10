package com.example.dmhelper.presentation.character.list

import android.R.attr.text
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.ClassEnum.BARBARIAN
import com.example.dmhelper.data.character.ClassEnum.BARD
import com.example.dmhelper.data.character.ClassEnum.CLERIC
import com.example.dmhelper.data.character.ClassEnum.DRUID
import com.example.dmhelper.data.character.ClassEnum.FIGHTER
import com.example.dmhelper.data.character.ClassEnum.MONK
import com.example.dmhelper.data.character.ClassEnum.PALADIN
import com.example.dmhelper.data.character.ClassEnum.RANGER
import com.example.dmhelper.data.character.ClassEnum.ROGUE
import com.example.dmhelper.data.character.ClassEnum.SORCERER
import com.example.dmhelper.data.character.ClassEnum.WARLOCK
import com.example.dmhelper.data.character.ClassEnum.WIZARD
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.character.toEnumOrNull
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.common.getResource
import com.example.dmhelper.presentation.components.board.ItemBoard
import com.example.dmhelper.presentation.components.board.TopBoard
import com.example.dmhelper.presentation.components.button.AddButton
import com.example.dmhelper.presentation.components.dialog.CreateCharacterDialog
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CharacterListScreen(
    navController: NavHostController,
    viewModel: CharacterListViewModel = koinViewModel()
) {
    val characterList by viewModel.characterFormState.collectAsStateWithLifecycle()
    viewModel.getCharacterList()
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_farm), contentScale = ContentScale.Crop)
    ) { _ ->
        val (wFactor, hFactor) = getFactors()
        val scrollState = rememberScrollState()
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(end = (24 * wFactor).dp)
            ) {
                TopBoard("Characters", {})
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(end = (24).dp, top = (16).dp, bottom = 16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.End
            ) {
                for (char in characterList.character) {
                    val classEnum = char.classId.toEnumOrNull<ClassEnum>()
                    val raceEnum = char.raceId.toEnumOrNull<RaceEnum>()
                    val painter = rememberVectorPainter(image = ImageVector.vectorResource(getResource(classEnum)))
                    ItemBoard(
                        text = char.name,
                        LeftIcon = { modifier ->
                            Icon(
                                painter = painter,
                                contentDescription = "Class Icon",
                                tint = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = modifier
                            )
                        },
                        subtext = "${raceEnum?.name} ${classEnum?.name}",
                        onClick = {})
                    Spacer(Modifier.height(16.dp))
                }
                AddButton(modifier = Modifier.width(492.dp), onClick = { openDialog.value = true })
            }
        }
        if (openDialog.value) {
            CreateCharacterDialog(
                onDismissRequest = { openDialog.value = false },
                onLeftButtonClicked = {},
                onRightButtonClicked = {navController.navigate(ScreenRoute.CreateCharacterRoute.route)}
            )
        }
    }
}


//@SuppressLint("ViewModelConstructorInComposable")
//@OrientationPreview
//@Composable
//fun CharacterListPreview() {
//    DMHelperTheme {
//        CharacterListScreen(
//            viewModel = CharacterListViewModel(),
//            navController = rememberNavController()
//        )
//    }
//}