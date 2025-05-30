package com.example.dmhelper.presentation.register

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.compose.LocalLifecycleOwner
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.lifecycle.repeatOnLifecycle
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.booksharing.R
//import com.example.booksharing.navigation.Graph
//import com.example.booksharing.navigation.authgraph.AuthScreen
//import com.example.booksharing.presentation.authscreen.login.LoginFormState
//import com.example.booksharing.presentation.authscreen.login.LoginViewModel
//import com.example.booksharing.presentation.common.AuthEvent
//import com.example.dmhelper.presentation.components.button.PrimaryButton
//import com.example.dmhelper.presentation.components.input.PasswordInput
//import com.example.booksharing.presentation.components.input.UsernameInput
//import com.example.booksharing.ui.theme.BookSharingTheme
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
//    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = MaterialTheme.colorScheme.surface)
//            .padding(horizontal = 16.dp)
//            .imePadding(),
//        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
//    ) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            LoginForm(
//                loginFormState = loginFormState,
//                onUsernameChanged = { newValue -> viewModel.onUsernameChanged(newValue) },
//                onPasswordChanged = { newValue -> viewModel.onPasswordChanged(newValue) },
//                onForgotPassword = { navController.navigate(AuthScreen.Forgot.route) },
//                modifier = Modifier.weight(1f)
//            )
//            LoginConfirm(
//                buttonState = loginFormState.isDataValid,
//                onButtonPressed = { viewModel.login() },
//                onNoAccount = { navController.navigate(AuthScreen.Register.route) },
//            )
//            LoginResult(
//                eventChannel = viewModel.eventChannel,
//                onLoginSuccess = {
//                    navController.navigate(Graph.MAIN) {
//                        popUpTo(Graph.ROOT) { inclusive = true }
//                        launchSingleTop = true
//                    }
//                    viewModel.clear()
//                },
//                snackbarState = snackbarHostState
//            )
//        }
//    }
}
//
//@Composable
//private fun LoginConfirm(
//    buttonState: Boolean,
//    onButtonPressed: () -> Unit,
//    onNoAccount: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier.fillMaxWidth()) {
//        PrimaryButton(
//            text = stringResource(R.string.sign_in).uppercase(),
//            isEnabled = buttonState,
//            onClick = { onButtonPressed.invoke() },
//        )
//        Spacer(Modifier.height(6.dp))
//        Text(
//            text = buildAnnotatedString {
//                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
//                    append(stringResource(R.string.no_account))
//                }
//                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
//                    append(stringResource(R.string.sign_up_command))
//                }
//            },
//            style = MaterialTheme.typography.bodySmall,
//            textDecoration = TextDecoration.Underline,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .clickable { onNoAccount.invoke() }
//        )
//        Spacer(Modifier.height(40.dp))
//    }
//}
//
//@Composable
//private fun LoginForm(
//    loginFormState: LoginFormState,
//    onUsernameChanged: (String) -> Unit,
//    onPasswordChanged: (String) -> Unit,
//    onForgotPassword: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier.fillMaxWidth()) {
//        Spacer(Modifier.padding(vertical = 70.dp))
//        //Title
//        Text(
//            text = stringResource(R.string.sign_in),
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(Modifier.height(20.dp))
//        UsernameInput(
//            action = { newValue -> onUsernameChanged.invoke(newValue) },
//            state = loginFormState.usernameFormState
//        )
//        Spacer(Modifier.height(6.dp))
//        PasswordInput(
//            action = { newValue -> onPasswordChanged.invoke(newValue) },
//            state = loginFormState.passwordFormState,
//            placeholder = stringResource(R.string.password)
//        )
//        // Forgot Password Link
//        Spacer(Modifier.height(12.dp))
//        Text(
//            text = stringResource(R.string.forgot_password),
//            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .clickable { onForgotPassword.invoke() },
//            color = MaterialTheme.colorScheme.secondary,
//            textDecoration = TextDecoration.Underline,
//            fontWeight = FontWeight.SemiBold
//        )
//    }
//}
//
//@Composable
//private fun LoginResult(
//    eventChannel: Flow<AuthEvent>,
//    onLoginSuccess: () -> Unit,
//    snackbarState: SnackbarHostState
//) {
//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    val defaultError = stringResource(R.string.error_login_network)
//    LaunchedEffect(key1 = Unit) {
//        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
//            launch {
//                eventChannel.collectLatest { event ->
//                    handleEvents(event, onLoginSuccess, snackbarState, defaultError)
//                }
//            }
//        }
//    }
//}
//
//private suspend fun handleEvents(
//    event: AuthEvent,
//    onSuccess: () -> Unit,
//    snackbarState: SnackbarHostState,
//    defaultError: String
//) {
//    when (event) {
//        is AuthEvent.AuthSuccess -> {
//            onSuccess.invoke()
//        }
//
//        is AuthEvent.AuthError -> {}
//        AuthEvent.ServerError -> {
//            snackbarState.showSnackbar(defaultError)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    BookSharingTheme {
//        LoginScreen(navController = rememberNavController())
//    }
//}