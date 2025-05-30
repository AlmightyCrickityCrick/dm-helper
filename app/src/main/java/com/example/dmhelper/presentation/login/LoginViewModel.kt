package com.example.dmhelper.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.presentation.common.FieldFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val _loginFormState = MutableStateFlow(
        LoginFormState(
            usernameFormState = FieldFormUiState(),
            passwordFormState = FieldFormUiState(),
            isDataValid = false
        )
    )
    val loginFormState: StateFlow<LoginFormState> = _loginFormState.asStateFlow()

    fun onUsernameChanged(text: String) {
        _loginFormState.update { previous ->
            previous.copy(
                usernameFormState = previous.usernameFormState.copy(
                    fieldText = text,
                ),
            )
        }
        validateInputs()
    }

    fun onPasswordChanged(newValue: String) {
        _loginFormState.update { previous ->
            previous.copy(
                passwordFormState = previous.passwordFormState.copy(
                    fieldText = newValue
                )
            )
        }
        validateInputs()
    }

    private fun validateInputs() = with(_loginFormState.value) {
        val (isPasswordValid, passwordError) = Pair(true, "")
        val (isUsernameValid, usernameError) = Pair(true, "")

        _loginFormState.update {
            it.copy(
                isDataValid = isPasswordValid && isUsernameValid,
                passwordFormState = it.passwordFormState.copy(
                    isError = !isPasswordValid || !it.passwordFormState.fieldText.isEmpty()
                ),
                usernameFormState = it.usernameFormState.copy(
                    isError = !isUsernameValid || !it.usernameFormState.fieldText.isEmpty()
                )
            )
        }
    }

    fun clear() {
        _loginFormState.update {
            LoginFormState(
                usernameFormState = FieldFormUiState(),
                passwordFormState = FieldFormUiState(),
                isDataValid = false
            )
        }
    }

    fun login() {
        viewModelScope.launch {
//            val result = repository.login(
//                AuthDTO(
//                    username = loginFormState.value.usernameFormState.fieldText.trim(),
//                    password = loginFormState.value.passwordFormState.fieldText.trim()
//                )
//            )
//            when (result) {
//                is Result.Success -> {
//                    _eventChannel.send(AuthEvent.AuthSuccess)
//                }
//
//                is Result.ServerError -> {
//                    _eventChannel.send(AuthEvent.ServerError)
//                }
//
//                is Result.ValidationError -> {
            _loginFormState.update { prev ->
                LoginFormState(
                    usernameFormState = FieldFormUiState(
                        fieldText = prev.usernameFormState.fieldText,
                        isError = true
                    ),
                    passwordFormState = FieldFormUiState(
                        fieldText = "",
                        isError = true
                    )
                )
//                    }
            }
        }
    }
}