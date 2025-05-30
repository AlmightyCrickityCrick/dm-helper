package com.example.dmhelper.presentation.components.input

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.FieldFormUiState

@Composable
fun PasswordInput(
    action: (String) -> Unit,
    state: FieldFormUiState,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.password),
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    PrimaryInput(
        initialValue = state.fieldText,
        placeholder = placeholder,
        isError = state.isError,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = { newValue -> action.invoke(newValue) },
        modifier = modifier,
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_password),
                contentDescription = stringResource(R.string.password_toggle),
                modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
            )
        }
    )
}