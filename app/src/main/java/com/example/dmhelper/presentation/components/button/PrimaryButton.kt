package com.example.dmhelper.presentation.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dmhelper.R
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun PrimaryButton(
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RectangleShape,
        content = { Text(text = text, style = MaterialTheme.typography.titleMedium) }
    )
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    DMHelperTheme  {
        PrimaryButton(stringResource(R.string.login), true, {})
    }
}
