package com.toulousehvl.iloveread.data.repository.remote.service

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.repository.remote.response.BookResponse

interface BookRepository {
    suspend fun getBookDetails(query: String): APIResult<BookResponse>
}