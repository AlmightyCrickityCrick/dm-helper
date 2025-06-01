package com.example.dmhelper.presentation.components.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
fun TopBoard(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp, start = 24.dp)
            .clickable { onClick.invoke() }
    ) {
        // Hanging rods
        Column(modifier = Modifier.align(Alignment.TopStart).offset(x = 10.dp, y = (-20).dp)) {
            Box(Modifier.width(4.dp).height(20.dp).background(MaterialTheme.colorScheme.onBackground))
        }
        Column(modifier = Modifier.align(Alignment.TopEnd).offset(x = (-10).dp, y = (-20).dp)) {
            Box(Modifier.width(4.dp).height(20.dp).background(MaterialTheme.colorScheme.onBackground))
        }

        // Wooden board
        val (wFactor, hFactor) = getFactors()
        Box(
            modifier = Modifier
                .height((50 * wFactor).dp)
                .width((250 * wFactor).dp)
                .clip(RoundedCornerShape(4.dp))
                .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
                .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
                .border(2.dp, MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBoard(){
    DMHelperTheme {
        TopBoard(text = "Ave, Username", {})
    }
}