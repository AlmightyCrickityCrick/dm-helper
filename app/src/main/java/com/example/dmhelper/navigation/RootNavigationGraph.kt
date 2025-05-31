package com.example.dmhelper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dmhelper.navigation.Graph

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost( navController = navController, route = Graph.ROOT, startDestination = Graph.SPLASH_SCREEN) {
        for (r in Graph.NAVIGATION_ROUTES) {
            composable(route = r.route) {
                r.Screen(navController)
            }
        }
    }
}