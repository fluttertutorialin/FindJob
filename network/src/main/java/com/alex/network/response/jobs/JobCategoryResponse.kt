package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobCategoryResponse(
    @SerialName("label")
    val label: String? = null,
    @SerialName("tag")
    val tag: String? = null
)