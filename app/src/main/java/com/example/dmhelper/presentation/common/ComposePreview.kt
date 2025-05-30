package com.example.dmhelper.presentation.common

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Tablet", showBackground = true, device = Devices.TABLET)
@Preview(
    name = "Phone",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 852,
    heightDp = 393
)
annotation class OrientationPreviews