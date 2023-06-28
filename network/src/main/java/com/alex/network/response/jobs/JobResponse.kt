package com.alex.network.response.jobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobResponse(
    @SerialName("category")
    val category: JobCategoryResponse?,
    @SerialName("salary_max")
    val salaryMax: Double?,
    @SerialName("location")
    val location: JobLocationResponse?,
    @SerialName("company")
    val company: JobCompanyResponse?,
    @SerialName("title")
    val title: String?,
    @SerialName("salary_is_predicted")
    val salaryIsPredicted: String?,
    @SerialName("redirect_url")
    val redirectUrl: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("contract_time")
    val contractTime: String?,
    @SerialName("adref")
    val adref: String?,
    @SerialName("contract_type")
    val contractType: String?,
    @SerialName("created")
    val created: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("salary_min")
    val salaryMin: Double?
)