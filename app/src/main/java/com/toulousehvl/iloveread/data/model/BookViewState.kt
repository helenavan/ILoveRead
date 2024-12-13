package com.toulousehvl.iloveread.data.model

sealed class BookViewState {
    data object IDLE: BookViewState()
    data object Loading: BookViewState()
    data class BookList(val books: MutableList<VolumeInfo> = mutableListOf<VolumeInfo>()): BookViewState()
    data class Error(val error: String? = null): BookViewState()
}