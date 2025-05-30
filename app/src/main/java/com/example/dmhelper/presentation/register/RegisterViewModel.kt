package com.example.dmhelper.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksharing.R
import com.example.booksharing.data.user.model.AuthDTO
import com.example.booksharing.data.user.repo.UserRepository
import com.example.booksharing.di.qualifiers.PasswordInputValidator
import com.example.booksharing.di.qualifiers.UsernameInputValidator
import com.example.booksharing.presentation.authscreen.register.RegisterFormState
import com.example.booksharing.presentation.common.AuthEvent
import com.example.booksharing.presentation.common.FieldFormUiState
import com.example.booksharing.utils.auth.BaseInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.Typography.dagger

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository,
    @UsernameInputValidator private val usernameValidator: BaseInputValidator,
    @EmailInputValidator private val usernameValidator: BaseInputValidator,
    @PasswordInputValidator private val passwordValidator: BaseInputValidator
) : ViewModel() {

    private val _registerFormState = MutableStateFlow(
        RegisterFormState(
            usernameFormState = FieldFormUiState(),
            passwordFormState = FieldFormUiState(),
            isDataValid = false
        )
    )
    val registerFormState: StateFlow<RegisterFormState> = _registerFormState.asStateFlow()
    private val _eventChannel = Channel<AuthEvent>()
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
        val (isPasswordValid, passwordError) = passwordValidator.validateInput(passwordFormState.fieldText)
        val (isUsernameValid, usernameError) = usernameValidator.validateInput(usernameFormState.fieldText)

        _registerFormState.update {
            it.copy(
                isDataValid = isPasswordValid && isUsernameValid,
                passwordFormState = it.passwordFormState.copy(
                    fieldError = passwordError,
                    isValid = isPasswordValid || it.passwordFormState.fieldText.isEmpty()
                ),
                usernameFormState = it.usernameFormState.copy(
                    fieldError = usernameError,
                    isValid = isUsernameValid || it.usernameFormState.fieldText.isEmpty()
                )
            )
        }
    }

    fun clear() {
        _registerFormState.update {
            RegisterFormState(
                usernameFormState = FieldFormUiState(),
                passwordFormState = FieldFormUiState(),
                isDataValid = false
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            val result = repository.register(
                AuthDTO(
                    username = registerFormState.value.usernameFormState.fieldText.trim(),
                    password = registerFormState.value.passwordFormState.fieldText.trim()
                )
            )
            when (result) {
                is Result.Success -> {
                    _eventChannel.send(AuthEvent.AuthSuccess)
                }

                is Result.ServerError -> {
                    _eventChannel.send(AuthEvent.ServerError)
                }

                is Result.ValidationError -> {
                    _registerFormState.update { prev ->
                        RegisterFormState(
                            usernameFormState = FieldFormUiState(
                                fieldText = prev.usernameFormState.fieldText,
                                fieldError = prev.usernameFormState.fieldError,
                                isValid = false
                            ),
                            passwordFormState = FieldFormUiState(
                                fieldText = "",
                                fieldError = R.string.error_register_incorrect_username_or_password,
                                isValid = false
                            )
                        )
                    }
                    _eventChannel.send(AuthEvent.AuthError(error = result.error.toString())) //For now
                }
            }
        }
    }
}