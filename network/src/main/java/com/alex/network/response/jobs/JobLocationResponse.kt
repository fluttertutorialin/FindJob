package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobLocationResponse(
    @SerialName("area")
    val area: List<String>? = null,
    @SerialName("display_name")
    val displayName: String? = null
)