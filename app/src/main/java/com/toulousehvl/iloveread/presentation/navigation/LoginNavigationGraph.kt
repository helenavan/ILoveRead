package com.toulousehvl.iloveread.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toulousehvl.iloveread.presentation.screens.Login.LoginScreen
import com.toulousehvl.iloveread.presentation.screens.home.HomeScreen
import com.toulousehvl.iloveread.presentation.screens.register.RegisterScreen

@Composable
fun LoginNavigationGraph(
    navHostController: NavHostController = rememberNavController(),
    authenticationNavigationViewModel: AuthNavigationViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = if (authenticationNavigationViewModel.isLoggedInState.value)
            NavigationGraph.LoginScreen.route
        else
            NavigationGraph.HomeScreen.route
    ) {
        composable(
            route = NavigationGraph.HomeScreen.route
        ) {
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = NavigationGraph.LoginScreen.route
        ) {
            LoginScreen(navHostController)
        }
        composable(
            route = NavigationGraph.RegisterScreen.route
        ) {
            RegisterScreen(navHostController)
        }
    }
}