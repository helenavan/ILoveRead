package com.toulousehvl.iloveread.data.repository.remote.service

import com.toulousehvl.iloveread.data.core.response.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("volumes")
    suspend fun getBooksDetails(@Query("q") query: String): Response<BookResponse>

}