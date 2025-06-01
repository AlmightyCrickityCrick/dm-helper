package com.example.dmhelper.presentation.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.R
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun AddButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .weight(0.4f)
                .height(3.dp)
                .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f))
        )
        Icon(
            modifier = Modifier.padding(horizontal = 12.dp).size(48.dp),
            painter = painterResource(R.drawable.ic_add),
            contentDescription = "Add More",
            tint = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
        )
        Box(
            Modifier
                .weight(0.4f)
                .height(3.dp)
                .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddButton() {
    DMHelperTheme {
        Box(Modifier
            .fillMaxHeight()
            .background(Color.Black)
        ) {
            AddButton(onClick = {})
        }
    }
}