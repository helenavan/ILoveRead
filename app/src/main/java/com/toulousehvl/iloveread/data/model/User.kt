package com.toulousehvl.iloveread.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var uID:String? = null,
    var pseudoFirebase:String? = null,
    var email:String? = null,
    var pathPhoto:String? = null,
    var bookList:List<VolumeInfo>? = emptyList()
)
