package com.toulousehvl.iloveread.data.repository.remote.service

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.model.Book
import com.toulousehvl.iloveread.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun login(email: String, password: String): Flow<APIResult<AuthResult>> =
        flow {
            try {
                emit(APIResult.Loading)
                val data = auth.signInWithEmailAndPassword(email, password).await()
                emit(APIResult.Success(data))
            } catch (e: Exception) {
                emit(APIResult.Error(e.localizedMessage ?: "Oops, something went wrong."))
            }
        }

    override suspend fun register(email: String, password: String): Flow<APIResult<AuthResult>> =
        flow {
            try {
                emit(APIResult.Loading)
                val data = auth.createUserWithEmailAndPassword(email, password).await()
                emit(APIResult.Success(data))
            } catch (e: Exception) {
                emit(APIResult.Error(e.localizedMessage ?: "Oops, something went wrong."))
            }
        }

    override suspend fun resetPassword(email: String): Flow<APIResult<Any>> = flow {
        try {
            emit(APIResult.Loading)
            val data = auth.sendPasswordResetEmail(email).await()
            emit(APIResult.Success(data))
        } catch (e: Exception) {
            emit(APIResult.Error(e.localizedMessage ?: "Oops, something went wrong."))
        }
    }

    override suspend fun logout() = auth.signOut()

    override suspend fun userUid(): String = auth.currentUser?.uid ?: ""

    override suspend fun isLoggedIn(): Boolean = auth.currentUser == null


    override suspend fun getListBooks(): Flow<APIResult<List<Book>>> =
        flow {
            try {
                val data : MutableList<Book>?= mutableListOf()
                emit(APIResult.Loading)
                firestore.document(userUid()).collection("books").get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            data?.map { document.toObjects(Book::class.java)}

                            Log.d("UserImpl", "getListBooks 0 ===> : $data")
                        }
                    }
                Log.d("UserImpl", "getListBooks current user ===>  ${auth.currentUser?.uid}")
                Log.d("UserImpl", "getListBooks ===> : $data")

                emit(APIResult.Success(data ?: emptyList()))
            } catch (e: Exception) {
                emit(APIResult.Error(e.localizedMessage ?: "Oops, something went wrong."))
            }
        }

    override suspend fun addBook(book: Book): Flow<APIResult<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBook(book: Book): Flow<APIResult<Boolean>> {
        TODO("Not yet implemented")
    }

}