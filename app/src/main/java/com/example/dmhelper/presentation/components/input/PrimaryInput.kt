package com.example.dmhelper.presentation.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun PrimaryInput(
    modifier: Modifier = Modifier,
    initialValue: String,
    placeholder: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val color =
            if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary
        TextField(
            value = initialValue,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = placeholder,
                    color = color,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = color
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            trailingIcon = trailingIcon,
            modifier = modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = color,
                        start = Offset(20f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryInputPreview() {
    DMHelperTheme {
        Scaffold(backgroundColor = Color.Black) { _ ->
            PrimaryInput(
                initialValue = "".trim(),
                placeholder = "Username",
                onValueChange = { },
                modifier = Modifier.padding(horizontal = 0.dp)
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PrimaryInputErrorPreview() {
//    DMHelperTheme {
//        PrimaryInput(
//            "".trim(),
//            stringResource(R.string.password),
//            true,
//            trailingIcon = {
//                Icon(
//                    imageVector = ImageVector.vectorResource(R.drawable.ic_password),
//                    contentDescription = stringResource(R.string.password_toggle),
//                    modifier = Modifier.clickable {
//                    })
//            },
//            onValueChange = TODO(),
//            visualTransformation = TODO(),
//            modifier = TODO(),
//        )
//    }
//}