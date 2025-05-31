package com.example.dmhelper.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dmhelper.R
import com.example.dmhelper.presentation.common.OrientationPreviews
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.common.getScreenSizeDp
import com.example.dmhelper.presentation.components.Board
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.presentation.components.button.RoundButtonSizes
import com.example.dmhelper.presentation.login.LoginViewModel
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun HomeScreen(navController: NavHostController,
               viewModel: HomeViewModel = koinViewModel(),
) {
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_main), contentScale = ContentScale.Crop)
    ) { _ ->
        Board("Ave, ${viewModel.getUsername()}", {})
        val (wFactor, hFactor) = getFactors()
        // Positioned circular buttons (like in the image)
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = (24 * wFactor).dp)
        ) {
            RoundButton(
                size = RoundButtonSizes.M,
                text = "Characters",
                onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-225 * wFactor).dp, y = (-90 * wFactor).dp)
            )

            RoundButton(
                size = RoundButtonSizes.L,
                text = "Campaigns",
                onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-34 * wFactor).dp, y = (0).dp)
            )

            RoundButton(
                "Settings", onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-200 * wFactor).dp, y = (110 * wFactor).dp)
            )
        }
    }
}


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun HomePreviewDryRun() {
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_main), contentScale = ContentScale.Crop)
    ) { _ ->
        Board("Ave, Username", {})
        val (wFactor, hFactor) = getFactors()
        // Positioned circular buttons (like in the image)
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = (24 * wFactor).dp)
        ) {
            RoundButton(
                size = RoundButtonSizes.M,
                text = "Characters",
                onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-225 * wFactor).dp, y = (-90 * wFactor).dp)
            )

            RoundButton(
                size = RoundButtonSizes.L,
                text = "Campaigns",
                onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-34 * wFactor).dp, y = (0).dp)
            )

            RoundButton(
                "Settings", onClick = { /*navController.navigate(...)*/ },
                modifier = Modifier
                    .offset(x = (-200 * wFactor).dp, y = (110 * wFactor).dp)
            )
        }
    }
}

@OrientationPreviews
@Composable
fun HomePreview() {
    DMHelperTheme {
        HomePreviewDryRun()
    }
}