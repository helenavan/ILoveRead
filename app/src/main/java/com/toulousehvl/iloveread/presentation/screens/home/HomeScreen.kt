package com.toulousehvl.iloveread.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.toulousehvl.iloveread.presentation.navigation.Screens

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                homeViewModel.logout()
                navHostController.navigate(Screens.LoginScreen.route) { popUpTo(0) }
            },
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(text = "Logout")
        }
    }
}