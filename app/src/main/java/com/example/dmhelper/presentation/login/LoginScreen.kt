package com.example.dmhelper.presentation.login


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import com.example.dmhelper.data.common.Result
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.booksharing.presentation.components.input.SimpleInput
import com.example.dmhelper.R
import com.example.dmhelper.data.user.dto.LoginResponseDTO
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.presentation.components.input.PasswordInput
import com.example.dmhelper.ui.theme.DMHelperTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavHostController
) {
    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_login), contentScale = ContentScale.Crop)
            .padding(horizontal = 16.dp)
            .imePadding()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BlurryCard(modifier = Modifier) {
                LoginForm(
                    loginFormState = loginFormState,
                    onUsernameChanged = { newValue -> viewModel.onUsernameChanged(newValue) },
                    onPasswordChanged = { newValue -> viewModel.onPasswordChanged(newValue) },
                    buttonState = loginFormState.isDataValid,
                    onButtonPressed = { viewModel.login() },
                    onNoAccount = {navController.navigate(ScreenRoute.RegisterRoute.route)}
                )
                LoginResult(
                    eventChannel = viewModel.eventChannel,
                    onLoginSuccess ={navController.navigate(ScreenRoute.HomeRoute.route)}
                )
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun LoginScreenContent() {
    val viewModel = LoginViewModel()
    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_login), contentScale = ContentScale.Crop)
            .padding(horizontal = 16.dp)
            .imePadding()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BlurryCard(modifier = Modifier) {
                LoginForm(
                    loginFormState = loginFormState,
                    onUsernameChanged = { newValue -> viewModel.onUsernameChanged(newValue) },
                    onPasswordChanged = { newValue -> viewModel.onPasswordChanged(newValue) },
                    buttonState = loginFormState.isDataValid,
                    onButtonPressed = { viewModel.login() },
                    onNoAccount = {}
                )
            }
        }
    }
}


@Composable
private fun LoginForm(
    loginFormState: LoginFormState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    buttonState: Boolean,
    onButtonPressed: () -> Unit,
    onNoAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
        .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(Modifier.height(15.dp).fillMaxHeight(0.4f))
        SimpleInput(
            action = { newValue -> onUsernameChanged.invoke(newValue) },
            state = loginFormState.usernameFormState
        )
        Spacer(Modifier.height(6.dp))
        PasswordInput(
            action = { newValue -> onPasswordChanged.invoke(newValue) },
            state = loginFormState.passwordFormState,
            placeholder = stringResource(R.string.password)
        )
        Spacer(Modifier.fillMaxHeight(0.4f))
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
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onNoAccount.invoke() }
        )
    }
}

@Composable
fun BlurryCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight(0.6f)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(10.dp, BlurredEdgeTreatment.Unbounded)
        ){
            Image(
                painter = painterResource(id = R.drawable.bg_login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
        }
            content()
    }
}


@Composable
private fun LoginResult(
    eventChannel: Flow<Result<LoginResponseDTO>>,
    onLoginSuccess: () -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch { eventChannel.collectLatest { event -> if(event is Result.Success) onLoginSuccess() } }
        }
    }
}

@OrientationPreview
@Composable
fun LoginScreenPreviewTablet() {
    DMHelperTheme {
        LoginScreenContent()
    }
}