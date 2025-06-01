package com.example.dmhelper.presentation.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dmhelper.R
import com.example.dmhelper.navigation.Graph
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun RoundButton(
    text: String,
    onClick: () -> Unit,
    size: RoundButtonSizes = RoundButtonSizes.DEFAULT,
    modifier: Modifier = Modifier,
    icon: Int? = null
) {
    val (wFactor, hFactor) = getFactors()
    Box(
        modifier = modifier
            .height(size.height.times( wFactor))
            .width(size.width.times(wFactor))
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer)  // wood-like tone
            .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
            .clickable(onClick = { onClick.invoke() })
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onBackground, shape = CircleShape)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
            style = when(size){
                RoundButtonSizes.DEFAULT -> MaterialTheme.typography.bodyLarge
                RoundButtonSizes.M -> MaterialTheme.typography.titleLarge
                RoundButtonSizes.L -> MaterialTheme.typography.headlineSmall
                RoundButtonSizes.XL -> MaterialTheme.typography.headlineLarge
                RoundButtonSizes.S -> MaterialTheme.typography.labelSmall
            }
        )
    }
}

enum class RoundButtonSizes(val height: Dp, val width: Dp) {
    DEFAULT(130.dp, 120.dp),
    M(147.dp, 130.dp),
    L(180.dp, 160.dp),
    XL(205.dp, 180.dp),
    S(62.dp, 55.dp)
}

@Preview(showBackground = true)
@Composable
fun previewRoundedButton() {
    DMHelperTheme {
        RoundButton("Characters", {})
    }
}