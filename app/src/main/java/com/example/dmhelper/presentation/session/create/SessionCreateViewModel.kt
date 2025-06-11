package com.example.dmhelper.presentation.session.create

import android.net.Uri
import android.util.Log
import android.util.Log.i
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksharing.presentation.mainscreen.upload.image.ImageState
import com.example.dmhelper.R
import com.example.dmhelper.data.campaign.CampaignRepository
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.data.character.CharacterRepository
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.session.CreateSessionDTO
import com.example.dmhelper.data.session.CreateSessionResponseDTO
import com.example.dmhelper.data.session.SessionRepository
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.PickerFormUiState
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
    private val campaignRepository: CampaignRepository
) : ViewModel() {
    private val _sessionFormState = MutableStateFlow(
        SessionCreateFormState(
            nameFormState = FieldFormUiState(),
            mapInputState = PickerFormUiState(
                chosenOption = 0,
                options = mutableMapOf(
                    0 to R.drawable.map_garden.toString(),
                    1 to R.drawable.map_desert_tomb.toString()
                )
            ),
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
            var characterId = campaignRepository.getCampaignCharacters(campaignId)
            val charLst = characterRepository.getCharactersById(characterId)
            Log.d("Repository", "campaignid= ${campaignId}, charId=$characterId, charLst=$charLst")
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
                val inputState = previous.mapInputState
                inputState.options.put(inputState.options.size, mapName)
                previous.copy(
                    mapFormState = ImageState(
                        imageUri = uri,
                        isSelected = true,
                        isValid = true,
                        imageFile = file,
                        imageType = type
                    ),
                    mapInputState = previous.mapInputState.copy(
                        options = inputState.options,
                        chosenOption = inputState.options.size - 1
                    )
                )
            }
        }
    }

    fun onMapSelected(newOption: Int) {
        _sessionFormState.update { previous ->
            previous.copy(
                mapInputState =
                    previous.mapInputState.copy(
                        chosenOption = newOption
                    )
            )
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
                        map = it.mapInputState.options[it.mapInputState.chosenOption].toString(),
                        charIdAllowed = it.characterSelection
                    )
                )
                _eventChannel.send(response)
            }
        }
    }
}