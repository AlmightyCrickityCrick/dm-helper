package com.example.dmhelper.presentation.login

import com.example.dmhelper.presentation.common.FieldFormUiState

data class LoginFormState(
    var usernameFormState: FieldFormUiState,
    var passwordFormState: FieldFormUiState,
    var isDataValid: Boolean = false
)