package com.toulousehvl.iloveread.data.model

import kotlinx.serialization.Serializable

//TODO service repository book
@Serializable
data class SearchInfo(
    var textSnippet: String? = null
)
