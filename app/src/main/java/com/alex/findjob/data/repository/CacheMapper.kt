package com.alex.findjob.data.repository

import com.alex.findjob.extensions.displayMonth
import com.alex.findjob.extensions.getLocalDateTime
import com.alex.findjob.screens.main.currency
import com.alex.findjob.screens.main.model.SearchModel
import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobCategoryResponse
import com.alex.network.response.jobs.JobCategoryResponseForCache
import com.alex.network.response.jobs.JobCompanyResponse
import com.alex.network.response.jobs.JobCompanyResponseForCache
import com.alex.network.response.jobs.JobLocationResponse
import com.alex.network.response.jobs.JobLocationResponseForCache
import com.alex.network.response.jobs.JobResponse
import com.alex.network.response.jobs.JobResponseForCache
import com.alex.network.response.jobs.JobsResponse
import com.alex.network.response.jobs.JobsResponseForCache
import javax.inject.Inject

class CacheMapper @Inject constructor() {

    fun mapSearchModelToSearchRequest(searchModel: SearchModel): SearchRequest =
        SearchRequest(
            countryTag = searchModel.countryTag,
            searchTag = searchModel.searchTag,
            locationTag = searchModel.locationTag,
            fullTimeTag = searchModel.fullTimeTag,
            partTimeTag = searchModel.partTimeTag,
            page = searchModel.page,
            resultsPerPage =  searchModel.resultsPerPage
        )

    fun mapJobsResponseToJobsForCache(jobsResponse: JobsResponse, countryTag: String): JobsResponseForCache =
        JobsResponseForCache(
            count = jobsResponse.count,
            results = jobsResponse.results?.let { mapListJobResponseToListJobForCache(it, countryTag) }
        )

    private fun mapListJobResponseToListJobForCache(
        jobListResponse: List<JobResponse>,
        countryTag: String
    ): List<JobResponseForCache> {
        val jobList = ArrayList<JobResponseForCache>()
        for (i in jobListResponse.indices) {
            jobList.add(mapJobResponseForCache(jobListResponse[i], countryTag))
        }
        return jobList
    }

    private fun mapJobResponseForCache(jobResponse: JobResponse, countryTag: String): JobResponseForCache =
        JobResponseForCache(
            category = jobResponse.category?.let { mapJobCategoryResponseToCache(it) },
            location = jobResponse.location?.let { mapJobLocationResponseToCache(it) },
            company = jobResponse.company?.let { mapJobCompanyResponseToCache(it) },
            salaryMax = jobResponse.salaryMax,
            title = jobResponse.title,
            salaryIsPredicted = jobResponse.salaryIsPredicted,
            redirectUrl = jobResponse.redirectUrl,
            description = jobResponse.description,
            contractTime = jobResponse.contractTime,
            adref = jobResponse.adref,
            contractType = jobResponse.contractType,
            created = jobResponse.created?.let {
                getLocalDateTime(it).dayOfMonth.toString() + " " +
                        getLocalDateTime(it).month.displayMonth() + " " +
                        getLocalDateTime(it).year.toString()
            },
            id = jobResponse.id,
            salaryMin = jobResponse.salaryMin,
            salary = currency[countryTag] + jobResponse.salaryMin?.toInt() + " - "
                    + currency[countryTag] + jobResponse.salaryMax?.toInt()
        )

    private fun mapJobCategoryResponseToCache(jobCategoryResponse: JobCategoryResponse): JobCategoryResponseForCache =
        JobCategoryResponseForCache(
            label = jobCategoryResponse.label,
            tag = jobCategoryResponse.tag
        )

    private fun mapJobLocationResponseToCache(jobLocationResponse: JobLocationResponse): JobLocationResponseForCache =
        JobLocationResponseForCache(
            area = jobLocationResponse.area,
            displayName = jobLocationResponse.displayName
        )

    private fun mapJobCompanyResponseToCache(jobCompanyResponse: JobCompanyResponse): JobCompanyResponseForCache =
        JobCompanyResponseForCache(
            displayName = jobCompanyResponse.displayName,
        )
}