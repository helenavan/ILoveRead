package com.toulousehvl.iloveread.data.repository.remote.service

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserImpl @Inject constructor(private val auth: FirebaseAuth) : UserRepository {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

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

}