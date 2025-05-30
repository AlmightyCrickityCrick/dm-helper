package com.example.dmhelper.presentation.components.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.compose.DMHelperTheme
import com.example.dmhelper.R

@Composable
fun PrimaryInput(
    initialValue: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = initialValue,
            onValueChange = { onValueChange(it) },
            isError = isError,
            placeholder = { Text(text = placeholder, style = MaterialTheme.typography.bodyLarge) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                errorBorderColor = MaterialTheme.colorScheme.error,
            ),
            maxLines = 1,
            trailingIcon = trailingIcon
        )
        if (errorMessage != null && isError) {
            Text(
                text = errorMessage,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                modifier = Modifier
                    .padding(top = 0.dp, start = 10.dp)
                    .defaultMinSize(minHeight = 26.dp),
                color = MaterialTheme.colorScheme.error,
                lineHeight = 1.em
            )
        } else {
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryInputPreview() {
    DMHelperTheme {
        PrimaryInput(
            "Hello".trim(),
            stringResource(R.string.enter_username),
            {},
            isError = false,
            errorMessage = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryInputErrorPreview() {
    DMHelperTheme {
        PrimaryInput(
            "".trim(),
            stringResource(R.string.enter_password),
            {},
            isError = true,
            errorMessage = stringResource(R.string.too_short),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_password),
                    contentDescription = stringResource(R.string.password_toggle),
                    modifier = Modifier.clickable {
                    })
            })
    }
}