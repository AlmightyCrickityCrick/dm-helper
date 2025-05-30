package com.example.dmhelper.presentation.register

import com.example.dmhelper.presentation.common.FieldFormUiState


data class RegisterFormState(
    var usernameFormState: FieldFormUiState,
    var emailFormState: FieldFormUiState,
    var passwordFormState: FieldFormUiState,
    var isDataValid: Boolean = false
)