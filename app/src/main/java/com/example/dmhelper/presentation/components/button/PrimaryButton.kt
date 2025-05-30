package com.example.dmhelper.presentation.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.ui.theme.DMHelperTheme
import com.example.dmhelper.R

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
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface
        ),
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
