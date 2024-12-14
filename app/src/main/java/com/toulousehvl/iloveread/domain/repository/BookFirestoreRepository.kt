package com.toulousehvl.iloveread.domain.repository

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.model.Book

//TODO
interface BookFirestoreRepository {

    suspend fun getListBooks(): APIResult<List<Book>>

    suspend fun addBook(book: Book): APIResult<Boolean>

    suspend fun deleteBook(book: Book): APIResult<Boolean>

}