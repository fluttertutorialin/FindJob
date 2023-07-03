package com.alex.findjob.data.repository

import com.alex.findjob.screens.main.model.JobCategory
import com.alex.findjob.screens.main.model.JobCompany
import com.alex.findjob.screens.main.model.JobLocation
import com.alex.findjob.screens.main.model.JobModel
import com.alex.findjob.screens.main.model.JobsModel
import com.alex.findjob.screens.main.model.SearchModel
import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobCategoryResponseForCache
import com.alex.network.response.jobs.JobCompanyResponseForCache
import com.alex.network.response.jobs.JobLocationResponseForCache
import com.alex.network.response.jobs.JobResponseForCache
import com.alex.network.response.jobs.JobsResponseForCache
import javax.inject.Inject

class JobsMapper @Inject constructor() {

    fun mapSearchRequestToSearchModel(searchRequest: SearchRequest): SearchModel =
        SearchModel(
            countryTag = searchRequest.countryTag,
            searchTag = searchRequest.searchTag,
            locationTag = searchRequest.locationTag,
            fullTimeTag = searchRequest.fullTimeTag,
            partTimeTag = searchRequest.partTimeTag,
            page = searchRequest.page,
            resultsPerPage = searchRequest.resultsPerPage
        )

    fun mapJobsResponseToJobs(jobsResponse: JobsResponseForCache): JobsModel =
        JobsModel(
            count = jobsResponse.count,
            results = jobsResponse.results?.let { mapListJobResponseToListJob(it) }
        )

    private fun mapListJobResponseToListJob(
        jobListResponse: List<JobResponseForCache>,
    ): List<JobModel> {
        val jobModelList = ArrayList<JobModel>()
        for (i in jobListResponse.indices) {
            jobModelList.add(mapJobResponseToJob(jobListResponse[i]))
        }
        return jobModelList
    }

    private fun mapJobResponseToJob(jobResponse: JobResponseForCache): JobModel =
        JobModel(
            category = jobResponse.category?.let { mapJobCategoryResponseToJobCategory(it) },
            location = jobResponse.location?.let { mapJobLocationResponseToJobLocation(it) },
            company = jobResponse.company?.let { mapJobCompanyResponseToJobCompany(it) },
            salaryMax = jobResponse.salaryMax,
            title = jobResponse.title,
            salaryIsPredicted = jobResponse.salaryIsPredicted,
            redirectUrl = jobResponse.redirectUrl,
            description = jobResponse.description,
            contractTime = jobResponse.contractTime,
            adref = jobResponse.adref,
            contractType = jobResponse.contractType,
            created = jobResponse.created,
            id = jobResponse.id,
            salaryMin = jobResponse.salaryMin,
            salary = jobResponse.salary
        )

    private fun mapJobCategoryResponseToJobCategory(jobCategoryResponse: JobCategoryResponseForCache): JobCategory =
        JobCategory(
            label = jobCategoryResponse.label,
            tag = jobCategoryResponse.tag
        )

    private fun mapJobLocationResponseToJobLocation(jobLocationResponse: JobLocationResponseForCache): JobLocation =
        JobLocation(
            area = jobLocationResponse.area,
            displayName = jobLocationResponse.displayName
        )

    private fun mapJobCompanyResponseToJobCompany(jobCompanyResponse: JobCompanyResponseForCache): JobCompany =
        JobCompany(
            displayName = jobCompanyResponse.displayName,
        )

}
