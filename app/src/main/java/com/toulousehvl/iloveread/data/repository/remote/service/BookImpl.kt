package com.toulousehvl.iloveread.data.repository.remote.service

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.core.response.BookResponse
import com.toulousehvl.iloveread.domain.repository.BookRepository
import javax.inject.Inject


class BookImpl @Inject constructor(private val apiService: BookApiService) : BookRepository {
    override suspend fun getBookDetails(query: String): APIResult<BookResponse> {
        return try {
            val response = apiService.getBooksDetails("+isbn:$query")
            if (response.isSuccessful && response.body() != null) {
                APIResult.Success(response.body()!!)
            } else {
                APIResult.Error(Exception(response.message()).toString())
            }
        } catch (e: Exception) {
            APIResult.Error("Error : ${e.message}")
        }
    }
}
