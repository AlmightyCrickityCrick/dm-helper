package com.example.booksharing.presentation.components.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.components.input.PrimaryInput


@Composable
fun SimpleInput(
    action: (String) -> Unit,
    placeholder : Int = R.string.username,
    state: FieldFormUiState,
    modifier: Modifier = Modifier
) {
    PrimaryInput(
        initialValue = state.fieldText,
        placeholder = stringResource(R.string.username),
        isError = state.isError,
        onValueChange = { newValue -> action.invoke(newValue) },
        modifier = modifier
    )
}