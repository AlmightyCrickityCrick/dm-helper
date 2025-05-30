package com.example.dmhelper.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksharing.R
import com.example.booksharing.data.common.Result
import com.example.booksharing.data.user.model.AuthDTO
import com.example.booksharing.data.user.repo.UserRepository
import com.example.booksharing.di.qualifiers.PasswordInputValidator
import com.example.booksharing.di.qualifiers.UsernameInputValidator
import com.example.booksharing.presentation.authscreen.login.LoginFormState
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
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    @UsernameInputValidator private val usernameValidator: BaseInputValidator,
    @PasswordInputValidator private val passwordValidator: BaseInputValidator
) : ViewModel() {

    private val _loginFormState = MutableStateFlow(
        LoginFormState(
            usernameFormState = FieldFormUiState(),
            passwordFormState = FieldFormUiState(),
            isDataValid = false
        )
    )
    val loginFormState: StateFlow<LoginFormState> = _loginFormState.asStateFlow()
    private val _eventChannel = Channel<AuthEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

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
        val (isPasswordValid, passwordError) = passwordValidator.validateInput(passwordFormState.fieldText)
        val (isUsernameValid, usernameError) = usernameValidator.validateInput(usernameFormState.fieldText)

        _loginFormState.update {
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
            val result = repository.login(
                AuthDTO(
                    username = loginFormState.value.usernameFormState.fieldText.trim(),
                    password = loginFormState.value.passwordFormState.fieldText.trim()
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
                    _loginFormState.update { prev ->
                        LoginFormState(
                            usernameFormState = FieldFormUiState(
                                fieldText = prev.usernameFormState.fieldText,
                                fieldError = prev.usernameFormState.fieldError,
                                isValid = false
                            ),
                            passwordFormState = FieldFormUiState(
                                fieldText = "",
                                fieldError = R.string.error_login_incorrect_username_or_password,
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