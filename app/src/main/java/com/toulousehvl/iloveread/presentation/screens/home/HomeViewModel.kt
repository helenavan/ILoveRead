package com.toulousehvl.iloveread.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toulousehvl.iloveread.domain.usecase.auth.UserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: UserLogoutUseCase
) : ViewModel(){

    fun logout() = viewModelScope.launch {
        logoutUseCase.invoke()
    }

}