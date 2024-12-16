package com.toulousehvl.iloveread.presentation.screens.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.domain.usecase.GetDetailBookUseCase
import com.toulousehvl.iloveread.domain.usecase.auth.UserLoginUseCase
import com.toulousehvl.iloveread.domain.usecase.auth.UserResetPasswordUseCase
import com.toulousehvl.iloveread.domain.usecase.firestore.GetListBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val resetPasswordUseCase: UserResetPasswordUseCase,
    //TODO in other viewmodel
    private val testUseCase: GetDetailBookUseCase,
    private val getListBooksUseCase: GetListBooksUseCase
) : ViewModel() {

    private val _loginFlow = MutableSharedFlow<APIResult<AuthResult>>()
    val loginFlow = _loginFlow

    private val _resetPasswordFlow = MutableSharedFlow<APIResult<Any>>()
    val resetPasswordFlow = _resetPasswordFlow

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: MutableStateFlow<FirebaseUser?> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            userLoginUseCase.invoke(email, password).collect { response ->
                _loginFlow.emit(response)

                Log.d("LoginViewModel", "=== Login successful + ${_loginFlow}")
            }
        }
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        resetPasswordUseCase.invoke(email).collect { response ->
            _resetPasswordFlow.emit(response)
        }
    }

    fun test() {
        viewModelScope.launch {
            when (val result = testUseCase("9782253124221")) {
                is APIResult.Success -> {
                    // Handle success
                    Log.d("TestViewModel", "Success ===> ${result.data?.items?.get(0)}")
                }

                is APIResult.Error -> {
                    // Handle error
                    Log.d("TestViewModel", "Error ===> ${result.exception}")
                }

                APIResult.Loading -> TODO()
            }
        }
    }

    fun getListBooks() = viewModelScope.launch {
        getListBooksUseCase.invoke().collect { response ->
            when (response) {
                is APIResult.Success -> {
                    // Handle success
                    Log.d("LoginViewModel", "Success get books ===> ${response.data}")
                }

                is APIResult.Error -> {
                    // Handle error
                    Log.d("BooksFirestoreViewModel", "Error ===> ${response.exception}")
                }

                APIResult.Loading -> {
                    // Handle loading
                    false
                }
            }
        }
    }
}