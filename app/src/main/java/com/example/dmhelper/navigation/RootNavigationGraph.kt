package com.example.dmhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH_SCREEN
    ) {
        for (r in Graph.NAVIGATION_ROUTES) {
            composable(route = r.route) {
                r.Screen(navController)
            }
            composable<ScreenRoute.CampaignMainRoute> { backStackEntry ->
                val campaign: ScreenRoute.CampaignMainRoute = backStackEntry.toRoute()
                campaign.Screen(navController)
            }
            composable<ScreenRoute.SessionListRoute> { backStackEntry ->
                val session: ScreenRoute.SessionListRoute = backStackEntry.toRoute()
                session.Screen(navController)
            }
            composable<ScreenRoute.SessionCreateRoute> { backStackEntry ->
                val session: ScreenRoute.SessionCreateRoute = backStackEntry.toRoute()
                session.Screen(navController)
            }
        }
    }
}