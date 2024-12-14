package com.toulousehvl.iloveread.domain.repository

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.core.response.BookResponse

interface BookRepository {
    suspend fun getBookDetails(query: String): APIResult<BookResponse>
}