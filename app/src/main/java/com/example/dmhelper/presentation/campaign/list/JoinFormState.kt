package com.example.dmhelper.presentation.campaign.list

import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.PickerFormUiState

data class JoinFormState(
    val codeInputState : FieldFormUiState = FieldFormUiState(),
    val characterPickerState : PickerFormUiState = PickerFormUiState(
        chosenOption = 0,
        options = mutableMapOf(),
        isEmpty = true
    )
)
