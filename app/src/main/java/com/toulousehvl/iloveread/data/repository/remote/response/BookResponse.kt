package com.toulousehvl.iloveread.data.repository.remote.response

import com.google.gson.annotations.SerializedName
import com.toulousehvl.iloveread.data.model.BookItem

data class BookResponse(
    @field:SerializedName("items") val items: List<BookItem>
)