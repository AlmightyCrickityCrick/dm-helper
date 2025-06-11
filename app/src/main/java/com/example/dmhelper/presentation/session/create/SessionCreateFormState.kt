package com.example.dmhelper.presentation.session.create

import com.example.booksharing.presentation.mainscreen.upload.image.ImageState
import com.example.dmhelper.data.character.CharacterListDTO
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.PickerFormUiState

data class SessionCreateFormState(
    var campaignId: Int = -1,
    var nameFormState: FieldFormUiState,
    var mapFormState: ImageState,
    var characterSelection: ArrayList<Int>,
    var characterList: CharacterListDTO,
    var isDataValid: Boolean = false,
    val mapInputState: PickerFormUiState
)