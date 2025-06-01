package com.example.dmhelper.presentation.campaign.list

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dmhelper.R
import com.example.dmhelper.data.campaign.toRoute
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.components.board.ItemBoard
import com.example.dmhelper.presentation.components.board.TopBoard
import com.example.dmhelper.presentation.components.button.RoundButton
import com.example.dmhelper.presentation.components.dialog.CreateCampaignDialog
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CampaignListScreen(
    navController: NavHostController,
    viewModel: CampaignListViewModel = koinViewModel()
) {
    val campaignList by viewModel.campaignListState.collectAsStateWithLifecycle()
    viewModel.getCampaignList()
    val openDialog = remember { mutableStateOf(false) }
    val campaignForm by viewModel.campaignFormState.collectAsStateWithLifecycle()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_street), contentScale = ContentScale.Crop)
    ) { _ ->
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
                        .padding(start = 18.dp, end = 18.dp, bottom = 70.dp)
                ) {
                    RoundButton(text = "New", onClick = { openDialog.value = true })
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
                for (camp in campaignList.list) {
                    if (!camp.isOwner) {
                        ItemBoard(text = camp.name, onClick = {navController.navigate(camp.toRoute())})
                    } else {
                        ItemBoard(
                            text = camp.name,
                            LeftIcon = { modifier ->
                                Icon(
                                    painter = painterResource(R.drawable.ic_crown),
                                    contentDescription = "You are the DM here",
                                    tint = MaterialTheme.colorScheme.secondaryContainer,
                                    modifier = modifier
                                )
                            }, onClick = { navController.navigate(camp.toRoute()) })
                    }
                    Spacer(Modifier.height(16.dp))
                }

            }
        }
        if (openDialog.value) {
            CreateCampaignDialog(
                onDismissRequest = { openDialog.value = false },
                inputState = campaignForm,
                onInputChanged = { newValue -> viewModel.onCampaignFieldChanged(newValue) },
                onLeftButtonClicked = {
                    viewModel.createCampaign()
                    openDialog.value = false
                },
                onRightButtonClicked = { openDialog.value = false }
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@OrientationPreview
@Composable
fun CampaignListPreview() {
    DMHelperTheme {
        CampaignListScreen(
            navController = rememberNavController(),
            viewModel = CampaignListViewModel()
        )
    }
}