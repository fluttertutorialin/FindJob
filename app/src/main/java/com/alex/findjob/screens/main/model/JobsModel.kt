package com.alex.findjob.screens.main.model

import java.io.Serializable

data class JobsModel(
    val count: Int,
    val results: List<JobModel>?,
)

data class JobModel(
    val category: JobCategory?,
    val salaryMax: Double?,
    val location: JobLocation?,
    val company: JobCompany?,
    val title: String?,
    val salaryIsPredicted: Int?,
    val redirectUrl: String?,
    val description: String?,
    val contractTime: String?,
    val adref: String?,
    val contractType: String?,
    val created: String?,
    val id: String?,
    val salaryMin: Double?,
    val salary: String?
) : Serializable

data class JobCategory(
    val label: String?,
    val tag: String?
) : Serializable

data class JobLocation(
    val area: List<String>?,
    val displayName: String?
) : Serializable

data class JobCompany(
    val displayName: String?
) : Serializable

