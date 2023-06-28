package com.alex.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest(
    @SerialName("country_tag")
    val countryTag: String,
    @SerialName("search_tag")
    val searchTag: String,
    @SerialName("location_tag")
    val locationTag: String,
    @SerialName("full_time_tag")
    val fullTimeTag: Boolean,
    @SerialName("part_time_tag")
    val partTimeTag: Boolean,
    val page: Int
)