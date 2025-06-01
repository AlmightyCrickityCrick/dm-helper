package com.example.dmhelper.presentation.campaignlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
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
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.components.board.ItemBoard
import com.example.dmhelper.presentation.components.board.TopBoard
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CampaignListScreen(
    navController: NavHostController,
    viewModel: CampaignListViewModel = koinViewModel()
) {

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_street), contentScale = ContentScale.Crop)
    ) {  _ ->
        val (wFactor, hFactor) = getFactors()
        val scrollState = rememberScrollState()
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(end = (24 * wFactor).dp)
            ) {
                TopBoard("Campaigns", {})
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 18.dp, end =18.dp, bottom = 70.dp)
                ) {
                    RoundButton(text = "New", onClick = {})
                    Spacer(Modifier.width(20.dp))
                    RoundButton(text = "Join", onClick = {})
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(end = (24).dp, top = (16).dp, bottom = 16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.End
            ) {
                ItemBoard(
                    text = "Elaria",
                    LeftIcon = { modifier ->
                        Icon(
                            painter = painterResource(R.drawable.ic_crown),
                            contentDescription = "You are the DM here",
                            tint = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = modifier
                        )
                    }, onClick = {})
                Spacer(Modifier.height(16.dp))
                ItemBoard(
                    text = "Elaria",
                    LeftIcon = { modifier ->
                        Icon(
                            painter = painterResource(R.drawable.ic_rogue),
                            contentDescription = "Rogue Icon",
                            tint = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = modifier
                        )
                    }, subtext = "Human Bard", onClick = {})
                Spacer(Modifier.height(16.dp))
                ItemBoard(
                    text = "Elaria",
                    LeftIcon = { modifier ->
                        Icon(
                            painter = painterResource(R.drawable.ic_rogue),
                            contentDescription = "Rogue Icon",
                            tint = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = modifier
                        )
                    }, subtext = "Human Bard", onClick = {})
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@OrientationPreview
@Composable
fun CampaignListPreview() {
    DMHelperTheme {
        CampaignListScreen(navController = rememberNavController(), viewModel = CampaignListViewModel())
    }
}