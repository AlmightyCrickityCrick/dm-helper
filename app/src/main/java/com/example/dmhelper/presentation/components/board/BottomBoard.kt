package com.example.dmhelper.presentation.components.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun BottomBoard(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val (wFactor, hFactor) = getFactors()
    Column(
        modifier = Modifier
            .width((400 * wFactor).dp).height(((65 * wFactor) + 40).dp)
            .padding(top = 24.dp, start = 24.dp)
            .clickable { onClick.invoke() }
    ) {
        // Wooden board
        Box(
            modifier = Modifier
                .height((65 * wFactor).dp)
                .width((400 * wFactor).dp)
                .clip(RoundedCornerShape(4.dp))
                .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
                .border(2.dp, MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.width((400 * wFactor).dp).padding(horizontal = 30.dp)) {
            Box(
                Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Box(
                Modifier
                    .width(4.dp)
                    .height(20.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBoard() {
    DMHelperTheme {
        BottomBoard(text = "Ave, Username", {})
    }
}