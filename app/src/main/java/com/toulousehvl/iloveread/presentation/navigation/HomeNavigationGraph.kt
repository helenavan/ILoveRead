package com.toulousehvl.iloveread.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toulousehvl.iloveread.presentation.screens.home.HomeScreen

@Composable
fun HomeNavigationGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
           HomeScreen(navHostController)

            Log.d("HomeNavigationGraph", "=== HomeScreen")
        }
    }
}