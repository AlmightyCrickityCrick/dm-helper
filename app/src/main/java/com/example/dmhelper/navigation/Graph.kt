package com.example.dmhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dmhelper.presentation.login.LoginScreen
import com.example.dmhelper.presentation.register.RegisterScreen

object Graph {
    const val ROOT = "ROOT_GRAPH"
    const val SPLASH_SCREEN = "SPLASH_SCREEN"
    val NAVIGATION_ROUTES = listOf (
        ScreenRoute.SPLASH,
        ScreenRoute.LOGIN,
        ScreenRoute.REGISTER,
        ScreenRoute.HOME
        )
}

enum class ScreenRoute ( val route : String ) {
    LOGIN ( "LOGIN_ROUTE" ),
    HOME("HOME_ROUTE"),
    REGISTER("REGISTER_ROUTE"),
    SPLASH("SPLASH_ROUTE");

    @Composable
    fun Screen(navController: NavHostController)  = when (this) {
        LOGIN -> LoginScreen(navController = navController)
        REGISTER -> RegisterScreen(navController = navController)
        HOME ->    RegisterScreen(navController = navController)
        SPLASH -> AnimatedSplashScreen(navController)
    }
}