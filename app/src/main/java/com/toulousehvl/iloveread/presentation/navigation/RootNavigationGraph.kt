package com.toulousehvl.iloveread.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.toulousehvl.iloveread.presentation.screens.Login.LoginScreen
import com.toulousehvl.iloveread.presentation.screens.home.HomeScreen
import com.toulousehvl.iloveread.presentation.screens.register.RegisterScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController = rememberNavController(),
    authenticationNavigationViewModel: AuthNavigationViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = if (authenticationNavigationViewModel.isLoggedInState.value) "main_graph"
        else "auth_graph"
    ) {
        navigation(startDestination = Screens.LoginScreen.route, route = "auth_graph") {
            composable(route = Screens.LoginScreen.route) {
                LoginScreen(navController)
            }
            composable(route = Screens.RegisterScreen.route) {
                RegisterScreen(navController)
            }
        }
        navigation(startDestination = Screens.HomeScreen.route, route = "main_graph") {
            composable(route = Screens.HomeScreen.route) {
                HomeScreen(navController)
            }
            composable("profile") {
                //  ProfileScreen()
            }
        }
    }
}