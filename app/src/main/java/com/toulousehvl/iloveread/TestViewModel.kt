package com.toulousehvl.iloveread

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.repository.remote.service.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testRepository: BookRepository
): ViewModel() {

    fun test() {
        viewModelScope.launch {
            when (val result = testRepository.getBookDetails("9782253124221")) {
                is APIResult.Success -> {
                    // Handle success
                    Log.d("TestViewModel", "Success ===> ${result.data?.items?.get(0)}")
                }

                is APIResult.Error -> {
                    // Handle error
                    Log.d("TestViewModel", "Error ===> ${result.exception}")
                }
            }
        }
    }
}