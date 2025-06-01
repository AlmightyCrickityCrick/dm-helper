package com.example.dmhelper.presentation.components.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun ItemBoard(
    text: String,
    subtext: String? = null,
    onClick: () -> Unit,
    LeftIcon: @Composable (modifier: Modifier) -> Unit = {},
    RightIcon: @Composable (modifier: Modifier) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val (wFactor, hFactor) = getFactors()
    Box(
        modifier = Modifier
            .height((80 * wFactor).dp)
            .width((330 * wFactor).dp)
            .clip(RoundedCornerShape(4.dp))
            .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop)
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
            .border(2.dp, MaterialTheme.colorScheme.onBackground)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Row(Modifier.padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically) {
            LeftIcon(Modifier.size(42.dp))
            Column(Modifier.weight(1f).fillMaxHeight().padding(horizontal = 12.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall
                )
                if (subtext != null) Text(
                    text = subtext,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            RightIcon(Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterItemBoard() {
    DMHelperTheme {
        ItemBoard(text = "Elaria",
            LeftIcon ={ modifier ->  Icon( painter = painterResource(R.drawable.ic_rogue),
            contentDescription = "Rogue Icon",
                tint = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
        ) }, subtext = "Human Bard", onClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCampaignItemBoard() {
    DMHelperTheme {
        ItemBoard(text = "Meighty Nein",
            LeftIcon ={ modifier ->  Icon( painter = painterResource(R.drawable.ic_crown),
                contentDescription = "Rogue Icon",
                tint = MaterialTheme.colorScheme.secondaryContainer,
                modifier = modifier
            ) }, onClick = {})
    }
}