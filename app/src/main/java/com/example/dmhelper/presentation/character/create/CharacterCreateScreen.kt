package com.example.dmhelper.presentation.character.create

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booksharing.presentation.components.input.SimpleInput
import com.example.dmhelper.R
import com.example.dmhelper.data.character.AbilityType
import com.example.dmhelper.data.character.ClassEnum
import com.example.dmhelper.data.character.CreateCharacterResponseDTO
import com.example.dmhelper.data.character.ProficiencyLevel
import com.example.dmhelper.data.character.RaceEnum
import com.example.dmhelper.data.character.Skill
import com.example.dmhelper.data.character.toEnumOrNull
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.common.getResource
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.ui.theme.DMHelperTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CharacterCreateScreen(
    navController: NavHostController,
    viewModel: CharacterCreateViewModel = koinViewModel()
) {
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
    ) { innerPadding ->
        val (wFactor, hFactor) = getFactors()
        val formState by viewModel.formState.collectAsStateWithLifecycle()
        val mainScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp)
                .padding(innerPadding)
                .padding(32.dp)
                .verticalScroll(mainScroll)
        ) {
            TopSection(formState, { event -> viewModel.onEvent(event) })
            MiddleSection(formState, { event -> viewModel.onEvent(event) })
            SkillsSection(formState, { event -> viewModel.onEvent(event) })
//            BottomSection(formState, { event -> viewModel.onEvent(event) })
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                PrimaryButton(
                    text = "Create",
                    isEnabled = true,
                    onClick = { viewModel.createCharacter() },
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 30.dp)
                )
                PrimaryButton(
                    text = "Cancel",
                    isEnabled = true,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 30.dp)
                )
            }
        }
        CreateResult(
            eventChannel = viewModel.eventChannel,
            onCreateSuccess = { navController.popBackStack() }
        )
    }
}

@Composable
private fun CreateResult(eventChannel: Flow<Result<CreateCharacterResponseDTO>>, onCreateSuccess: () -> Unit) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch { eventChannel.collectLatest { event -> if (event is Result.Success) onCreateSuccess() } }
        }
    }
}

@Composable
private fun TopSection(formState: CharacterFormState, onEvent: (CharacterCreationEvent) -> Unit) {
    Row(Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Column(Modifier
            .weight(0.45f)
            .wrapContentHeight()) {
            SimpleInput(
                state = formState.name,
                action = { newValue -> onEvent(CharacterCreationEvent.NameChanged(newValue)) },
                placeholderInt = R.string.character_name,
                modifier = Modifier.fillMaxWidth()
            )
            Row(Modifier.fillMaxWidth()) {
                SimpleInput(
                    state = formState.background,
                    action = { newValue -> onEvent(CharacterCreationEvent.BackgroundChanged(newValue)) },
                    placeholderInt = R.string.background,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .padding(vertical = 25.dp, horizontal = 12.dp)
                    .border(
                        2.dp, MaterialTheme.colorScheme.onSecondary
                    )
                    .padding(vertical = 25.dp, horizontal = 12.dp)
            ) {
                Text(text = "Character Class", color = MaterialTheme.colorScheme.onSecondary, style = MaterialTheme.typography.bodyMedium)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .fillMaxHeight()
                ) {
                    items(ClassEnum.entries.size) { index ->
                        val charClass = ClassEnum.entries[index]
                        Icon(
                            painter = painterResource(getResource(charClass)),
                            tint = if (formState.selectedClass?.ordinal == index) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondaryContainer,
                            contentDescription = "Class Icon",
                            modifier = Modifier
                                .size(46.dp)
                                .clickable(onClick = {
                                    onEvent(CharacterCreationEvent.ClassChanged(charClass))
                                })
                        )
                    }
                }
            }
        }
        Spacer(Modifier.weight(0.1f))
        Column(Modifier
            .weight(0.45f)
            .wrapContentHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp, horizontal = 12.dp)
                    .border(
                        2.dp, MaterialTheme.colorScheme.onSecondary
                    )
                    .padding(vertical = 20.dp, horizontal = 12.dp)
                    .height(364.dp)
            ) {
                Text(text = "Character Race", color = MaterialTheme.colorScheme.onSecondary, style = MaterialTheme.typography.bodyMedium)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(vertical = 25.dp)
                ) {
                    items(RaceEnum.entries.size) { index ->
                        val race = RaceEnum.entries[index]
                        Icon(
                            painter = painterResource(getResource(race)),
                            tint = if (formState.selectedRace?.ordinal == index) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondaryContainer,
                            contentDescription = "Race Icon",
                            modifier = Modifier
                                .size(46.dp)
                                .clickable(onClick = {
                                    onEvent(CharacterCreationEvent.RaceChanged(race))
                                })
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MiddleSection(
    formState: CharacterFormState,
    onEvent: (CharacterCreationEvent) -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        Column(Modifier.weight(0.45f)) {
            SimpleInput(
                state = formState.alignment,
                action = { newValue -> onEvent(CharacterCreationEvent.AlignmentChanged(newValue)) },
                placeholderInt = R.string.character_alignment,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            SimpleInput(
                state = formState.personalityTraits,
                action = { newValue -> onEvent(CharacterCreationEvent.PersonalityTraitsChanged(newValue)) },
                placeholderInt = R.string.personality_traits,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            SimpleInput(
                state = formState.ideals,
                action = { newValue -> onEvent(CharacterCreationEvent.IdealsChanged(newValue)) },
                placeholderInt = R.string.ideals,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            SimpleInput(
                state = formState.flaws,
                action = { newValue -> onEvent(CharacterCreationEvent.FlawsChanged(newValue)) },
                placeholderInt = R.string.character_flaws,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
        Spacer(Modifier.weight(0.1f))
        Column(Modifier.weight(0.45f)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp, horizontal = 12.dp)
                    .border(
                        2.dp, MaterialTheme.colorScheme.onSecondary
                    )
                    .height(290.dp)
                    .padding(vertical = 20.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = "Abilities",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp)
                ) {
                    items(AbilityType.entries.size) { index ->
                        val ability = AbilityType.entries[index]
                        val state = when(ability) {
                            AbilityType.STRENGTH -> formState.abilities.strength
                            AbilityType.DEXTERITY -> formState.abilities.dexterity
                            AbilityType.CONSTITUTION -> formState.abilities.constitution
                            AbilityType.INTELLIGENCE -> formState.abilities.intelligence
                            AbilityType.WISDOM -> formState.abilities.wisdom
                            AbilityType.CHARISMA -> formState.abilities.charisma
                        }
                        Row(Modifier.fillMaxWidth()) {
                            Icon(
                                painter = painterResource(getResource(ability)),
                                tint = MaterialTheme.colorScheme.onSecondary,
                                contentDescription = "Ability Icon",
                                modifier = Modifier
                                    .size(46.dp)
                            )
                            SimpleInput(
                                state = state,
                                action = { newValue -> onEvent(CharacterCreationEvent.AbilityChanged(ability, newValue)) },
                                placeholder = ability.name.substring(0, 3),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillsSection(formState: CharacterFormState, onEvent: (CharacterCreationEvent) -> Unit) {
    Text(
        text = "Skills",
        color = MaterialTheme.colorScheme.onSecondary,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(start=20.dp, bottom=12.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 12.dp)
            .border(
                2.dp, MaterialTheme.colorScheme.onSecondary
            )
            .padding(vertical = 20.dp, horizontal = 12.dp)
            .height(356.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 25.dp)
        ) {
            items(Skill.entries.size) { index ->
                val skill = Skill.entries[index]
                val painter = when (formState.skillProficiencies[skill]) {
                    ProficiencyLevel.NONE -> painterResource(R.drawable.ic_prof_none)
                    ProficiencyLevel.PROFICIENT -> painterResource(R.drawable.ic_prof_prof)
                    ProficiencyLevel.EXPERT -> painterResource(R.drawable.ic_prof_exp)
                    null -> painterResource(R.drawable.ic_prof_none)
                }
                Row(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painter,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = "Ability Icon",
                        modifier = Modifier
                            .size(46.dp)
                            .clickable(onClick = { onEvent(CharacterCreationEvent.SkillProficiencyChanged(skill)) })
                    )
                    SimpleInput(
                        state = FieldFormUiState(),
                        action = {},
                        placeholder = Skill.entries[index].name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom =10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSection(formState: CharacterFormState, onEvent: (CharacterCreationEvent) -> Unit) {

}


@OrientationPreview
@Composable
fun PreviewTopSection() {
    DMHelperTheme {
        TopSection(CharacterFormState(), {})
    }
}

@OrientationPreview
@Composable
fun PreviewMiddleSection() {
    DMHelperTheme {
        MiddleSection(CharacterFormState(), {}, )
    }
}

@OrientationPreview
@Composable
fun PreviewSkillsSection() {
    DMHelperTheme {
        SkillsSection(CharacterFormState(), {})
    }
}

@OrientationPreview
@Composable
fun PreviewBottomSection() {
    DMHelperTheme {
        BottomSection(CharacterFormState(), {})
    }
}


//@SuppressLint("ViewModelConstructorInComposable")
//@OrientationPreview
//@Composable
//fun PreviewCharacterCreateScreen() {
//    DMHelperTheme {
//        CharacterCreateScreen(rememberNavController(), CharacterCreateViewModel())
//    }
//}