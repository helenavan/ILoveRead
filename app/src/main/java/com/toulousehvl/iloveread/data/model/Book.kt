package com.toulousehvl.iloveread.data.model

import java.io.Serializable

data class Book(
    var kind: String? = null,
    var totalItems: Int? = null,
    var items: List<BookItem>? = null
) : Serializable
