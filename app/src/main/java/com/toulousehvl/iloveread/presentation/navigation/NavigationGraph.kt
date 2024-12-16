package com.toulousehvl.iloveread.presentation.navigation

sealed class NavigationGraph(val route: String) {
    data object HomeScreen : NavigationGraph("home_screen")
    data object LoginScreen : NavigationGraph("login_screen")
    data object RegisterScreen : NavigationGraph("register_screen")
}