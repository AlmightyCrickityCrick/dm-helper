package com.example.dmhelper.presentation.login


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booksharing.presentation.components.input.UsernameInput
import com.example.dmhelper.R
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreviews
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.presentation.components.input.PasswordInput
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen (
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .imePadding(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LoginForm(
                loginFormState = loginFormState,
                onUsernameChanged = { newValue -> viewModel.onUsernameChanged(newValue) },
                onPasswordChanged = { newValue -> viewModel.onPasswordChanged(newValue) },
                modifier = Modifier.weight(1f)
            )
            LoginConfirm(
                buttonState = loginFormState.isDataValid,
                onButtonPressed = { viewModel.login() },
                onNoAccount = { navController.navigate(ScreenRoute.REGISTER) },
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun LoginScreenContent(){
    val viewModel = LoginViewModel()
    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp)
            .imePadding(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LoginForm(
                loginFormState = loginFormState,
                onUsernameChanged = { newValue -> viewModel.onUsernameChanged(newValue) },
                onPasswordChanged = { newValue -> viewModel.onPasswordChanged(newValue) },
                modifier = Modifier.weight(1f)
            )
            LoginConfirm(
                buttonState = loginFormState.isDataValid,
                onButtonPressed = { viewModel.login() },
                onNoAccount = {}
//                onNoAccount = { navController.navigate(ScreenRoute.REGISTER) },
            )
        }
    }
}


@Composable
private fun LoginConfirm(
    buttonState: Boolean,
    onButtonPressed: () -> Unit,
    onNoAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        PrimaryButton(
            text = stringResource(R.string.login).uppercase(),
            isEnabled = buttonState,
            onClick = { onButtonPressed.invoke() },
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.navigate_login),
            style = MaterialTheme.typography.bodySmall,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onNoAccount.invoke() }
        )
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
private fun LoginForm(
    loginFormState: LoginFormState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.padding(vertical = 70.dp))
        //Title
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))
        UsernameInput(
            action = { newValue -> onUsernameChanged.invoke(newValue) },
            state = loginFormState.usernameFormState
        )
        Spacer(Modifier.height(6.dp))
        PasswordInput(
            action = { newValue -> onPasswordChanged.invoke(newValue) },
            state = loginFormState.passwordFormState,
            placeholder = stringResource(R.string.password)
        )
    }
}

@OrientationPreviews
@Composable
fun LoginScreenPreviewTablet() {
    DMHelperTheme {
        LoginScreenContent()
    }
}