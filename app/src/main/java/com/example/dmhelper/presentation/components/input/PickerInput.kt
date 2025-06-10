package com.example.dmhelper.presentation.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.PickerFormUiState
import com.example.dmhelper.ui.theme.DMHelperTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickerInput(
    pickerFormState: PickerFormUiState,
    onItemSelected: (Int) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        PrimaryInput(
            initialValue = pickerFormState.options.get(pickerFormState.chosenOption) ?: "",
            placeholder = placeholder,
            isError = false,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded,
                    modifier = modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
                )
            },
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            containerColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(260.dp)
        ) {
            pickerFormState.options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption.value,
                            color = MaterialTheme.colorScheme.onSecondary,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, start = 12.dp)
                                ,

                            )
                    },
                    onClick = {
                        onItemSelected(selectionOption.key)
                        isExpanded = false
                    },
                    modifier = Modifier.fillMaxWidth().height(40.dp).paint(
                        painterResource(
                            R.drawable.bg_wood
                        ), contentScale = ContentScale.FillBounds
                    ).border(3.dp, color = MaterialTheme.colorScheme.onSurface)

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPickerInput() {
    DMHelperTheme {
        PickerInput(
            PickerFormUiState(1, mutableMapOf(1 to "Creanga", 2 to "Eminescu", 3 to "Vieru")),
            {},
            placeholder = "Author"
        )
    }
}
