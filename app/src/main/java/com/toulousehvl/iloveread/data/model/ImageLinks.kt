package com.toulousehvl.iloveread.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    var smallThumbnail: String? = null,
    var thumbnail: String? = null,
    var small: String? = null,
    var medium: String? = null,
    var large: String? = null,
    var extraLarge: String? = null
)
