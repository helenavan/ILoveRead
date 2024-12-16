package com.toulousehvl.iloveread.domain.repository

import com.google.firebase.auth.AuthResult
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.model.Book
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun login(email: String, password: String): Flow<APIResult<AuthResult>>

    suspend fun register(email: String, password: String): Flow<APIResult<AuthResult>>

    suspend fun resetPassword(email: String): Flow<APIResult<Any>>

    suspend fun logout()

    suspend fun userUid(): String

    suspend fun isLoggedIn(): Boolean

    //Firestore

    suspend fun getListBooks(): Flow<APIResult<List<Book>>>

    suspend fun addBook(book: Book): Flow<APIResult<Boolean>>

    suspend fun deleteBook(book: Book): Flow<APIResult<Boolean>>

}