package com.example.dmhelper.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dmhelper.data.campaign.CampaignDTO
import com.example.dmhelper.data.session.CreateSessionDTO
import com.example.dmhelper.presentation.campaign.list.CampaignListScreen
import com.example.dmhelper.presentation.campaign.main.CampaignMainScreen
import com.example.dmhelper.presentation.character.create.CharacterCreateScreen
import com.example.dmhelper.presentation.character.list.CharacterListScreen
import com.example.dmhelper.presentation.home.HomeScreen
import com.example.dmhelper.presentation.login.LoginScreen
import com.example.dmhelper.presentation.register.RegisterScreen
import com.example.dmhelper.presentation.session.create.SessionCreateScreen
import com.example.dmhelper.presentation.session.editor.SessionEditorScreen
import com.example.dmhelper.presentation.session.list.SessionListScreen
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
    @Serializable
    data class SessionListRoute(val campaignId: Int) : ScreenRoute("sessions")
    @Serializable
    data class SessionCreateRoute(val campaignId: Int) : ScreenRoute("session/create")
    @Serializable
    data class SessionEditorRoute(val campaignId: Int, val imageSrc : String) : ScreenRoute("session/create")

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
            val campaign = CampaignDTO(this.campaignId, this.campaignName, this.isOwner)
            Log.d("Repository", "$campaignId")
            CampaignMainScreen(navController = navController, campaign = campaign)
        }
        is SessionListRoute -> SessionListScreen(navController = navController, campaignId = this.campaignId)
        is SessionCreateRoute -> SessionCreateScreen(navController=navController, campaignId = this.campaignId)
        is SessionEditorRoute -> SessionEditorScreen(navController = navController, createSessionDTO = CreateSessionDTO("", this.campaignId, this.imageSrc, arrayListOf()))
    }
}