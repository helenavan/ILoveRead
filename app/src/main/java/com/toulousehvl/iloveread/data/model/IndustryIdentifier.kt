package com.toulousehvl.iloveread.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifier(
    var type: String? = null,
    var identifier: String? = null //isbn is type ISBN_13
)
