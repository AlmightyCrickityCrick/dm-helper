package com.example.dmhelper.presentation.common

data class PickerFormUiState(
    val chosenOption: Int,
    val options: MutableMap<Int, String>,
    val isEmpty: Boolean = true
)
