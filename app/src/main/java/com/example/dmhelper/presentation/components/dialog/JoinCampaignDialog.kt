package com.example.dmhelper.presentation.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.booksharing.presentation.components.input.SimpleInput
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.FieldFormUiState
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.PickerFormUiState
import com.example.dmhelper.presentation.components.button.PrimaryButton
import com.example.dmhelper.presentation.components.input.PickerInput
import com.example.dmhelper.ui.theme.DMHelperTheme

@Composable
fun JoinCampaignDialog(
    onDismissRequest: () -> Unit,
    inputState: FieldFormUiState,
    onInputChanged: (newValue: String) -> Unit,
    pickerFormState: PickerFormUiState,
    onItemSelected: (Int) -> Unit,
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
                    text = "Join Campaign",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(top = 40.dp),
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    SimpleInput(
                        state = inputState,
                        placeholderInt = R.string.code,
                        action = onInputChanged, modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .weight(0.4f)
                            .height(50.dp)

                    )
                    PickerInput(
                        pickerFormState = pickerFormState,
                        placeholder = "Pick Character",
                        onItemSelected = { newValue -> onItemSelected.invoke(newValue) },
                        modifier = Modifier
                            .weight(0.4f).offset(y=(-7).dp)
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    PrimaryButton(
                        text = "join",
                        isEnabled = true,
                        onClick = {onLeftButtonClicked.invoke()},
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(horizontal = 30.dp)
                    )
                    PrimaryButton(
                        text = "Cancel",
                        isEnabled = true,
                        onClick = {onRightButtonClicked.invoke()},
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(horizontal = 30.dp)
                    )
                }
            }
        }
    }
}

@Composable
@OrientationPreview
fun PreviewJoinCampaignDialog() {
    DMHelperTheme {
        JoinCampaignDialog(
            {}, FieldFormUiState(), {}, PickerFormUiState(
            chosenOption = 1,
            options = mutableMapOf(),
            isEmpty = true
        ), {}, {}, {})
    }
}
