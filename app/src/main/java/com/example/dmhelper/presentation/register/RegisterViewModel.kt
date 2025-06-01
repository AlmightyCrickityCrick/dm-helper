package com.example.dmhelper.presentation.register


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.data.user.dto.RegisterDTO
import com.example.dmhelper.data.common.Result
import com.example.dmhelper.data.user.UserRepository
import com.example.dmhelper.data.user.UserRepositoryImpl
import com.example.dmhelper.presentation.common.FieldFormUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository = UserRepositoryImpl()) : ViewModel() {

    private val _registerFormState = MutableStateFlow(
        RegisterFormState(
            usernameFormState = FieldFormUiState(),
            emailFormState = FieldFormUiState(),
            passwordFormState = FieldFormUiState(),
            isDataValid = false
        )
    )
    val registerFormState: StateFlow<RegisterFormState> = _registerFormState.asStateFlow()
    private val _eventChannel = Channel<Result<LoginResponseDTO>>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun onUsernameChanged(text: String) {
        _registerFormState.update { previous ->
            previous.copy(
                usernameFormState = previous.usernameFormState.copy(
                    fieldText = text,
                ),
            )
        }
        validateInputs()
    }

    fun onEmailChanged(text: String) {
        _registerFormState.update { previous ->
            previous.copy(
                emailFormState = previous.emailFormState.copy(
                    fieldText = text,
                ),
            )
        }
        validateInputs()
    }

    fun onPasswordChanged(newValue: String) {
        _registerFormState.update { previous ->
            previous.copy(
                passwordFormState = previous.passwordFormState.copy(
                    fieldText = newValue
                )
            )
        }
        validateInputs()
    }

    private fun validateInputs() = with(_registerFormState.value) {
        val (isPasswordValid, passwordError) = Pair(true, "")
        val (isUsernameValid, usernameError) = Pair(true, "")

        _registerFormState.update {
            it.copy(
                isDataValid = isPasswordValid && isUsernameValid,
                passwordFormState = it.passwordFormState.copy(
                    isError = !isPasswordValid
                ),
                usernameFormState = it.usernameFormState.copy(
                    isError = !isUsernameValid
                )
            )
        }
    }

    fun clear() {
        _registerFormState.update {
            RegisterFormState(
                usernameFormState = FieldFormUiState(),
                passwordFormState = FieldFormUiState(),
                emailFormState = FieldFormUiState(),
                isDataValid = false
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            val result = repository.register(
                RegisterDTO(
                    username = registerFormState.value.usernameFormState.fieldText.trim(),
                    password = registerFormState.value.passwordFormState.fieldText.trim(),
                    email = registerFormState.value.emailFormState.fieldText.trim()
                )
            )
            when (result) {
                is Result.Success -> {
                    _eventChannel.send(result)
                }

                is Result.ServerError -> {}
                is Result.ValidationError -> {
                    _registerFormState.update { prev ->
                        RegisterFormState(
                            usernameFormState = FieldFormUiState(
                                fieldText = prev.usernameFormState.fieldText,
                                isError = true
                            ),
                            passwordFormState = FieldFormUiState(
                                fieldText = "",
                                isError = true
                            ),
                            emailFormState = FieldFormUiState(
                                fieldText = "",
                                isError = true
                            ),
                        )
//                    }
                    }
                }
            }
        }
    }
}