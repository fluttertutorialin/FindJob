package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobCompanyResponse(
    @SerialName("display_name")
    val displayName: String
)