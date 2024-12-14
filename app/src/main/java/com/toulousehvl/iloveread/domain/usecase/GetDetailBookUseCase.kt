package com.toulousehvl.iloveread.domain.usecase

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.core.response.BookResponse
import com.toulousehvl.iloveread.data.repository.remote.service.BookImpl
import javax.inject.Inject

class GetDetailBookUseCase @Inject constructor(private val bookRepository: BookImpl) {

    suspend operator fun invoke(query: String): APIResult<BookResponse> =
        bookRepository.getBookDetails(query)
}