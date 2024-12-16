package com.toulousehvl.iloveread.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.domain.usecase.auth.UserRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: UserRegisterUseCase
) : ViewModel(){
    private val _registerFlow = MutableSharedFlow<APIResult<AuthResult>>()
    val registerFlow = _registerFlow

    fun register(email: String, password: String) = viewModelScope.launch {
        registerUseCase.invoke(email, password).collect { response ->
            _registerFlow.emit(response)
        }
    }
}