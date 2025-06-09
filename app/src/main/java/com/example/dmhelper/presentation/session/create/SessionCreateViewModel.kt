package com.example.dmhelper.presentation.session.create

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksharing.presentation.mainscreen.upload.image.ImageState
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.session.CreateSessionDTO
import com.example.dmhelper.data.session.CreateSessionResponseDTO
import com.example.dmhelper.data.session.SessionRepository
import com.example.dmhelper.presentation.common.FieldFormUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class SessionCreateViewModel(
    private val repository: SessionRepository,
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    private val _sessionFormState = MutableStateFlow(
        SessionCreateFormState(
            nameFormState = FieldFormUiState(),
            mapInputState = FieldFormUiState(),
            mapFormState = ImageState(),
            characterList = CharacterListDTO(arrayListOf()),
            characterSelection = arrayListOf()
        )
    )
    val sessionFormState: StateFlow<SessionCreateFormState> = _sessionFormState.asStateFlow()
    private val _eventChannel = Channel<Result<CreateSessionResponseDTO>>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun setCampaignId(campaignId: Int) {
        viewModelScope.launch {
            val charLst = characterRepository.getAllCampaignCharacters(campaignId)
            _sessionFormState.update { previous ->
                previous.copy(
                    characterList = previous.characterList.copy(character = charLst.character),
                    campaignId = campaignId
                )
            }
        }
    }

    fun uploadMap(uri: Uri, type: String?, file: File) {
        viewModelScope.launch {
            val mapName = repository.uploadMap(uri, type, file)
            _sessionFormState.update { previous ->
                previous.copy(
                    mapFormState = ImageState(
                        imageUri = uri,
                        isSelected = true,
                        isValid = true,
                        imageFile = file,
                        imageType = type
                    ),
                    mapInputState = FieldFormUiState(fieldText = mapName)
                )
            }
        }
    }

    fun onNameChanged(newValue: String) {
        _sessionFormState.update { previous ->
            previous.copy(
                nameFormState = previous.nameFormState.copy(fieldText = newValue)
            )
        }
    }

    fun onUserListChanged(newId: Int) {
        _sessionFormState.update { previous ->
            val newCharacterList = previous.characterSelection.toMutableList()
            if (newId in sessionFormState.value.characterSelection) newCharacterList.remove(newId)
            else newCharacterList.add(newId)
            previous.copy(
                characterSelection = newCharacterList as ArrayList,
            )
        }
        Log.d("SessionCreateView", sessionFormState.value.characterSelection.toString())
    }

    fun createSession() {
        _sessionFormState.value.let {
            viewModelScope.launch {
                val response = repository.createSession(
                    CreateSessionDTO(
                        name = it.nameFormState.fieldText,
                        campaignId = it.campaignId,
                        map = it.mapInputState.fieldText,
                        userIdAllowed = it.characterSelection
                    )
                )
                _eventChannel.send(response)
            }
        }
    }
}