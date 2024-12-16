package com.toulousehvl.iloveread.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toulousehvl.iloveread.domain.usecase.auth.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthNavigationViewModel @Inject constructor(
    private val isLoggedInUseCase: IsUserLoggedUseCase
) : ViewModel() {
    private var _isLoggedInState = mutableStateOf(false)
    val isLoggedInState = _isLoggedInState

    private fun isUserLogin() = viewModelScope.launch {
        isLoggedInUseCase().collect {
            _isLoggedInState.value = it
        }
    }
}