package com.example.dmhelper.presentation.session.list

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
import com.example.dmhelper.navigation.ScreenRoute
import com.example.dmhelper.presentation.common.OrientationPreview
import com.example.dmhelper.presentation.common.getFactors
import com.example.dmhelper.presentation.components.board.ItemBoard
import com.example.dmhelper.presentation.components.board.TopBoard
import com.example.dmhelper.presentation.components.button.AddButton
import com.example.dmhelper.ui.theme.DMHelperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun SessionListScreen(
    navController: NavHostController,
    viewModel: SessionListViewModel = koinViewModel(),
    campaignId: Int
) {
    val sessionList by viewModel.sessionFormState.collectAsStateWithLifecycle()
    viewModel.getSessionList(campaignId)
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0f),
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_forest), contentScale = ContentScale.Crop)
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
                TopBoard("Sessions", {})
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(end = (24).dp, top = (16).dp, bottom = 16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.End
            ) {
                for (ses in sessionList.list) {
                    ItemBoard(
                        text = ses.name,
                        RightIcon = { modifier ->
                            Icon(
                                painter = painterResource(R.drawable.ic_active),
                                contentDescription = "Class Icon",
                                tint = if (ses.isAccessible) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onBackground,
                                modifier = modifier
                            )
                        },
                        onClick = {})
                    Spacer(Modifier.height(16.dp))
                }
                AddButton(
                    modifier = Modifier.width(492.dp),
                    onClick = { navController.navigate(ScreenRoute.SessionCreateRoute(campaignId)) })
            }
        }
    }
}


//@SuppressLint("ViewModelConstructorInComposable")
//@OrientationPreview
//@Composable
//fun SessionListPreview() {
//    DMHelperTheme {
//        SessionListScreen(
//            viewModel = SessionListViewModel(),
//            navController = rememberNavController(),
//            campaignId = 2
//        )
//    }
//}