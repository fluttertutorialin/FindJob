package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobsResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<JobResponse>?,
)
