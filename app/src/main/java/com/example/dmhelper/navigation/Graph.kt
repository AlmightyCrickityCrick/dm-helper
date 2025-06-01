package com.example.dmhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.presentation.campaign.list.CampaignListScreen
import com.example.dmhelper.presentation.campaign.main.CampaignMainScreen
import com.example.dmhelper.presentation.character.create.CharacterCreateScreen
import com.example.dmhelper.presentation.character.list.CharacterListScreen
import com.example.dmhelper.presentation.home.HomeScreen
import com.example.dmhelper.presentation.login.LoginScreen
import com.example.dmhelper.presentation.register.RegisterScreen
import kotlinx.serialization.Serializable

object Graph {
    const val ROOT = "ROOT_GRAPH"
    const val SPLASH_SCREEN = "SPLASH_SCREEN"
    val NAVIGATION_ROUTES = listOf(
        ScreenRoute.SPLASH,
        ScreenRoute.LoginRoute,
        ScreenRoute.RegisterRoute,
        ScreenRoute.HomeRoute,
        ScreenRoute.CharacterListRoute,
        ScreenRoute.CampaignListRoute,
        ScreenRoute.CreateCharacterRoute
    )
}
@Serializable
sealed class ScreenRoute(val route: String) {
    data object LoginRoute : ScreenRoute("login")
    data object HomeRoute : ScreenRoute("home")
    data object RegisterRoute : ScreenRoute("register")
    data object SPLASH : ScreenRoute(Graph.SPLASH_SCREEN)
    data object CharacterListRoute : ScreenRoute("character_list")
    data object CreateCharacterRoute : ScreenRoute("character_create")
    data object CampaignListRoute : ScreenRoute("campaign_list")
    @Serializable
    data class CampaignMainRoute(val campaignId: Int, val campaignName: String, val isOwner : Boolean) : ScreenRoute("campaign")

    @Composable
    fun Screen(navController: NavHostController) = when (this) {
        LoginRoute -> LoginScreen(navController = navController)
        RegisterRoute -> RegisterScreen(navController = navController)
        HomeRoute -> HomeScreen(navController = navController)
        SPLASH -> AnimatedSplashScreen(navController)
        CharacterListRoute -> CharacterListScreen(navController = navController)
        CampaignListRoute -> CampaignListScreen(navController = navController)
        CreateCharacterRoute -> CharacterCreateScreen(navController = navController)
        is CampaignMainRoute -> {
            val campaign: CampaignDTO = CampaignDTO(this.campaignId, this.campaignName, this.isOwner)
            CampaignMainScreen(navController = navController, campaign = campaign)
        }
    }
}