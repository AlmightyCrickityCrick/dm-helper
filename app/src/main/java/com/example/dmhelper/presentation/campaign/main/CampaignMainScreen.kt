package com.example.dmhelper.presentation.campaign.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.presentation.character.create.CharacterCreateViewModel
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CampaignMainScreen(
    navController: NavHostController,
    campaign: CampaignDTO
) {
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_sew), contentScale = ContentScale.Crop)
    ) { _ ->
        val (wFactor, hFactor) = getFactors()

        Text(text = "${campaign.id}  ${campaign.name}", color = Color.Red)
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@OrientationPreview
@Composable
fun PreviewCampaignMainScreen() {
    DMHelperTheme {
        CampaignMainScreen(rememberNavController(), CampaignDTO(2, "Mighty Nein", true))
    }
}