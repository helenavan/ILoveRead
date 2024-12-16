package com.toulousehvl.iloveread.presentation.screens.AddBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.domain.usecase.firestore.GetListBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO
@HiltViewModel
class BooksFirestoreViewModel @Inject constructor(
    private val getListBooksUseCase: GetListBooksUseCase
) : ViewModel() {

    fun getListBooks() = viewModelScope.launch {
        getListBooksUseCase.invoke().collect { response ->
            when (response) {
                is APIResult.Success -> {
                    // Handle success
                    Log.d("BooksFirestoreViewModel", "Success ===> ${response.data}")
                }

                is APIResult.Error -> {
                    // Handle error
                }

                APIResult.Loading -> {
                    // Handle loading
                    TODO()
                }
            }
        }
    }
}