package com.example.dmhelper.presentation.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.booksharing.presentation.components.input.SimpleInput
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun CreateCampaignDialog(
    onDismissRequest: () -> Unit,
    inputState: FieldFormUiState,
    onInputChanged: (newValue: String) -> Unit,
    onLeftButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(20.dp)
                .paint(painterResource(R.drawable.bg_wood), contentScale = ContentScale.Crop),
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Create New Campaign",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 40.dp),
                )
                SimpleInput(
                    state= inputState,
                    placeholder = R.string.campaign_name,
                    action = onInputChanged, modifier = Modifier.padding(horizontal = 20.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    PrimaryButton(
                        text = "Create",
                        isEnabled = true,
                        onClick = onLeftButtonClicked,
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(horizontal = 30.dp)
                    )
                    PrimaryButton(
                        text = "Cancel",
                        isEnabled = true,
                        onClick = onRightButtonClicked,
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(horizontal = 30.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateCampaignDialog() {
    DMHelperTheme {
        CreateCharacterDialog({}, {}, {})
    }
}
