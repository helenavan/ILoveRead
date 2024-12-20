package com.toulousehvl.iloveread.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toulousehvl.iloveread.presentation.screens.Login.LoginScreen
import com.toulousehvl.iloveread.presentation.screens.register.RegisterScreen
import com.toulousehvl.iloveread.presentation.screens.home.HomeScreen


@Composable
fun LoginNavigationGraph(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(
            route = Screens.LoginScreen.route
        ) {
            LoginScreen(navHostController)
        }
        composable(
            route = Screens.RegisterScreen.route
        ) {
            RegisterScreen(navHostController)
        }
    }
}