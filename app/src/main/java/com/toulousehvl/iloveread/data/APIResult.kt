package com.toulousehvl.iloveread.data

import com.toulousehvl.iloveread.data.repository.remote.response.BookResponse

sealed class APIResult<out T: Any> {
    data class Success<out T: Any>(val data: BookResponse?): APIResult<T>()
    data class Error(val exception: String): APIResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${exception?.javaClass?.kotlin?.simpleName}]"
        }
    }
}