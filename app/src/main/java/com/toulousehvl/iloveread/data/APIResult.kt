package com.toulousehvl.iloveread.data

sealed class APIResult<out T: Any> {
    data object Loading : APIResult<Nothing>()
    data class Success<out T : Any>(val data: T) : APIResult<T>()
    data class Error(val exception: String): APIResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${exception.javaClass.kotlin.simpleName}]"
            Loading -> TODO()
        }
    }
}