package com.toulousehvl.iloveread.presentation.navigation

sealed class Screens(val route: String) {
    //TODO home hierarchy
    data object HomeScreen : Screens("home")
    data object LoginScreen : Screens("login")
    data object RegisterScreen : Screens("register")
}