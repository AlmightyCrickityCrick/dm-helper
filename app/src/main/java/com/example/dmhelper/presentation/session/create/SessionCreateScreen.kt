package com.example.dmhelper.presentation.session.create

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.session.CreateSessionResponseDTO
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.components.button.AddButton
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.presentation.session.create.image.ImagePicker
import com.example.dmhelper.ui.theme.DMHelperTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.io.File

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun SessionCreateScreen(
    navController: NavHostController,
    viewModel: SessionCreateViewModel = koinViewModel(),
    campaignId: Int
) {
    val sessionForm by viewModel.sessionFormState.collectAsStateWithLifecycle()
    viewModel.setCampaignId(campaignId)
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_forest), contentScale = ContentScale.Crop),
    ) { _ ->
        SessionCreateResult(eventChannel = viewModel.eventChannel, onCreateSuccess = { navController.popBackStack() })
        Card(
            modifier = Modifier
                .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
                .fillMaxSize(0.7f),
            colors = CardColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Row(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(0.45f).fillMaxHeight().padding(vertical = 42.dp)) {
                    LeftCreateScreen(
                        sessionForm = sessionForm,
                        onInputChanged = { newValue -> viewModel.onNameChanged(newValue)},
                        onMapChanged = { uri, type, file -> viewModel.uploadMap(uri, type, file) }
                    )
                    Spacer(Modifier.weight(1f))
                    PrimaryButton(
                        text = "Create",
                        isEnabled = true,
                        onClick = { viewModel.createSession() })
                }
                Spacer(Modifier.weight(0.1f))
                Column(modifier = Modifier.weight(0.45f).fillMaxHeight().padding(vertical = 42.dp)) {
                    CharacterSelectionScreen(
                        selectionList = sessionForm.characterList,
                        selectedCharacters = sessionForm.characterSelection,
                        onCharacterClicked = { characterId -> viewModel.onUserListChanged(characterId) }
                    )
                    Spacer(Modifier.weight(1f))
                    PrimaryButton(text = "Cancel", isEnabled = true, onClick = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
private fun LeftCreateScreen(sessionForm: SessionCreateFormState, onInputChanged: (newValue: String) -> Unit, onMapChanged: (Uri, String?, File) -> Unit) {
    SimpleInput(
        state = sessionForm.nameFormState,
        placeholder = R.string.session_name,
        action = {  newValue -> onInputChanged(newValue)})
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 20.dp)
    ) {
        SimpleInput(
            state = sessionForm.mapInputState,
            placeholder = R.string.map_name,
            action = {},
            modifier = Modifier.height(50.dp).weight(4f)
        )
        Spacer(Modifier.weight(2f))
        ImagePicker(
            imageState = sessionForm.mapFormState,
            onImageSelected = { uri, type, file -> onMapChanged(uri, type, file) },
        )
    }
}

@Composable
fun CharacterSelectionScreen(
    selectionList: CharacterListDTO,
    selectedCharacters: ArrayList<Int>,
    onCharacterClicked: (characterId: Int) -> Unit
) {
    val characterScroll = rememberScrollState()
    Box(
        modifier = Modifier
            .border(
                2.dp, MaterialTheme.colorScheme.onSecondary
            )
            .fillMaxHeight(0.8f)
            .verticalScroll(characterScroll)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "Characters Invited", style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
            for (char in selectionList.character) {
                Text(
                    text = char.name,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .border(
                            2.dp,
                            if (char.id in selectedCharacters) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        .padding(10.dp)
                        .clickable { onCharacterClicked.invoke(char.id) })
            }
            AddButton(onClick = {}) //Button to create invitation code
        }
    }
}

@Composable
private fun SessionCreateResult(
    eventChannel: Flow<Result<CreateSessionResponseDTO>>,
    onCreateSuccess: () -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch { eventChannel.collectLatest { event -> if (event is Result.Success) onCreateSuccess() } }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@OrientationPreview
@Composable
fun PreviewSessionCreateScreen() {
    DMHelperTheme {
        SessionCreateScreen(rememberNavController(), SessionCreateViewModel(), 2)
    }
}