package com.toulousehvl.iloveread.data.model

import java.io.Serializable

data class BookItem(
    var kind: String? = null,
    var id: String? = null,
    var etag: String? = null,
    var selfLink: String? = null,
    var volumeInfo: VolumeInfo? = null,
    var searchInfo: SearchInfo? = null
) : Serializable
