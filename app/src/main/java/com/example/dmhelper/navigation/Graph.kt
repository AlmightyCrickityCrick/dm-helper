package com.example.dmhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dmhelper.presentation.campaignlist.CampaignListScreen
import com.example.dmhelper.presentation.characterlist.CharacterListScreen
import com.example.dmhelper.presentation.home.HomeScreen
import com.example.dmhelper.presentation.login.LoginScreen
import com.example.dmhelper.presentation.register.RegisterScreen

object Graph {
    const val ROOT = "ROOT_GRAPH"
    const val SPLASH_SCREEN = "SPLASH_SCREEN"
    val NAVIGATION_ROUTES = listOf(
        ScreenRoute.SPLASH,
        ScreenRoute.LOGIN,
        ScreenRoute.REGISTER,
        ScreenRoute.HOME,
        ScreenRoute.CHARACTER_LIST,
        ScreenRoute.CAMPAIGN_LIST
    )
}

enum class ScreenRoute(val route: String) {
    LOGIN("LOGIN_ROUTE"),
    HOME("HOME_ROUTE"),
    REGISTER("REGISTER_ROUTE"),
    SPLASH(Graph.SPLASH_SCREEN),
    CHARACTER_LIST("CHARACTER_LIST_ROUTE"),
    CAMPAIGN_LIST("CAMPAIGN_LIST_ROUTE");


    @Composable
    fun Screen(navController: NavHostController) = when (this) {
        LOGIN -> LoginScreen(navController = navController)
        REGISTER -> RegisterScreen(navController = navController)
        HOME -> HomeScreen(navController = navController)
        SPLASH -> AnimatedSplashScreen(navController)
        CHARACTER_LIST -> CharacterListScreen(navController = navController)
        CAMPAIGN_LIST -> CampaignListScreen(navController = navController)
    }
}