package com.alex.findjob.screens.main.model

data class Jobs(
    val count: Int,
    val results: List<Job>?,
)

data class Job(
    val category: JobCategory?,
    val salaryMax: Double?,
    val location: JobLocation?,
    val company: JobCompany?,
    val title: String?,
    val salaryIsPredicted: String?,
    val redirectUrl: String?,
    val description: String?,
    val contractTime: String?,
    val adref: String?,
    val contractType: String?,
    val created: String?,
    val id: String?,
    val salaryMin: Double?,
    val salary: String?
)

data class JobCategory(
    val label: String,
    val tag: String
)

data class JobLocation(
    val area: List<String>,
    val displayName: String
)

data class JobCompany(
    val displayName: String
)
