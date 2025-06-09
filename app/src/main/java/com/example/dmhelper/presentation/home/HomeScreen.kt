package com.example.dmhelper.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.components.board.TopBoard
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.presentation.components.button.RoundButtonSizes
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
        TopBoard("Ave, ${viewModel.getUsername()}", {})
        val (wFactor, hFactor) = getFactors()
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = (24 * wFactor).dp)
        ) {
            RoundButton(
                size = RoundButtonSizes.M,
                text = "Characters",
                onClick = { navController.navigate(ScreenRoute.CharacterListRoute.route) },
                modifier = Modifier
                    .offset(x = (-225 * wFactor).dp, y = (-90 * wFactor).dp)
            )
            RoundButton(
                size = RoundButtonSizes.L,
                text = "Campaigns",
                onClick = { navController.navigate(ScreenRoute.CampaignListRoute.route) },
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

//@SuppressLint("ViewModelConstructorInComposable")
//@OrientationPreview
//@Composable
//fun HomePreview() {
//    DMHelperTheme {
//        HomeScreen(rememberNavController(), HomeViewModel())
//    }
//}