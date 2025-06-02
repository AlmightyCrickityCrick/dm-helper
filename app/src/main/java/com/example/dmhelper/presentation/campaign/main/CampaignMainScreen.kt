package com.example.dmhelper.presentation.campaign.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.components.board.BottomBoard
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.presentation.components.button.RoundButtonSizes
import com.example.dmhelper.ui.theme.DMHelperTheme

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
            .paint(painterResource(R.drawable.bg_dung), contentScale = ContentScale.Crop)
    ) { _ ->
        val (wFactor, hFactor) = getFactors()
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            RoundButton(
                size = RoundButtonSizes.S,
                text = "Characters",
                onClick = { navController.navigate(ScreenRoute.CharacterListRoute.route) },
                modifier = Modifier.padding(20.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(end = (24 * wFactor).dp)
            ) {

                RoundButton(
                    "Create Invite", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (-290 * wFactor).dp, y = (-30 * wFactor).dp)
                )
                RoundButton(
                    "Characters", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (-160 * wFactor).dp, y = (60 * wFactor).dp),
                    size = RoundButtonSizes.M
                )
                RoundButton(
                    "Campaign Summary", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (-160 * wFactor).dp, y = (-130 * wFactor).dp)
                )
                RoundButton(
                    size = RoundButtonSizes.XL,
                    text = "Sessions",
                    onClick = { navController.navigate(ScreenRoute.SessionListRoute(campaign.id)) },
                    modifier = Modifier
                        .offset(x = (0 * wFactor).dp, y = (-60).dp)
                )
                RoundButton(
                    "Maps", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (160 * wFactor).dp, y = (-130 * wFactor).dp)
                )
                RoundButton(
                    "Assets", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (160 * wFactor).dp, y = (60 * wFactor).dp),
                    size = RoundButtonSizes.M
                )
                RoundButton(
                    "NPC", onClick = { /*navController.navigate(...)*/ },
                    modifier = Modifier
                        .offset(x = (290 * wFactor).dp, y = (-30 * wFactor).dp),
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BottomBoard(
                    campaign.name,
                    onClick = {},
                )
            }
        }
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