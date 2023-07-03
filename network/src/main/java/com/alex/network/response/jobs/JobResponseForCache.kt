package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobsResponseForCache(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<JobResponseForCache>?,
)

@Serializable
data class JobResponseForCache(
    @SerialName("category")
    val category: JobCategoryResponseForCache? = null,
    @SerialName("salary_max")
    val salaryMax: Double? = null,
    @SerialName("location")
    val location: JobLocationResponseForCache? = null,
    @SerialName("company")
    val company: JobCompanyResponseForCache? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("salary_is_predicted")
    val salaryIsPredicted: Int? = null,
    @SerialName("redirect_url")
    val redirectUrl: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("contract_time")
    val contractTime: String? = null,
    @SerialName("adref")
    val adref: String? = null,
    @SerialName("contract_type")
    val contractType: String? = null,
    @SerialName("created")
    val created: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("salary_min")
    val salaryMin: Double? = null,
    @SerialName("salary")
    val salary: String? = null
)

@Serializable
data class JobCategoryResponseForCache(
    @SerialName("label")
    val label: String? = null,
    @SerialName("tag")
    val tag: String? = null
)

@Serializable
data class JobLocationResponseForCache(
    @SerialName("area")
    val area: List<String>? = null,
    @SerialName("display_name")
    val displayName: String? = null
)

@Serializable
data class JobCompanyResponseForCache(
    @SerialName("display_name")
    val displayName: String? = null
)
