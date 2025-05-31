package com.example.dmhelper.presentation.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun getScreenSizeDp(): Pair<Int, Int> {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    return screenWidthDp to screenHeightDp
}

@Composable
fun getFactors(): Pair<Float, Float> {
//    val (width, height) = getScreenSizeDp()
//    return width / 852 to height / 393
    return 1.5f to 2f
}