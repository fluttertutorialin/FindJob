package com.alex.findjob.data.repository

import com.alex.findjob.localization.russianLocale
import com.alex.findjob.screens.main.model.Job
import com.alex.findjob.screens.main.model.JobCategory
import com.alex.findjob.screens.main.model.JobCompany
import com.alex.findjob.screens.main.model.JobLocation
import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel
import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobCategoryResponse
import com.alex.network.response.jobs.JobCompanyResponse
import com.alex.network.response.jobs.JobLocationResponse
import com.alex.network.response.jobs.JobResponse
import com.alex.network.response.jobs.JobsResponse
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale
import javax.inject.Inject

class JobsMapper @Inject constructor() {

    fun mapSearchModelToSearchRequest(searchModel: SearchModel): SearchRequest =
        SearchRequest(
            countryTag = searchModel.countryTag,
            searchTag = searchModel.searchTag,
            locationTag = searchModel.locationTag,
            fullTimeTag = searchModel.fullTimeTag,
            partTimeTag = searchModel.partTimeTag,
            page = searchModel.page
        )

    fun mapSearchRequestToSearchModel(searchRequest: SearchRequest): SearchModel =
        SearchModel(
            countryTag = searchRequest.countryTag,
            searchTag = searchRequest.searchTag,
            locationTag = searchRequest.locationTag,
            fullTimeTag = searchRequest.fullTimeTag,
            partTimeTag = searchRequest.partTimeTag,
            page = searchRequest.page
        )

    fun mapJobsResponseToJobs(jobsResponse: JobsResponse): Jobs =
        Jobs(
            count = jobsResponse.count,
            results = jobsResponse.results?.let { mapListJobResponseToListJob(it) }
        )

    private fun mapListJobResponseToListJob(jobListResponse: List<JobResponse>): List<Job> {
        val jobList = ArrayList<Job>()
        for (i in jobListResponse.indices) {
            jobList.add(mapJobResponseToJob(jobListResponse[i]))
        }
        return jobList
    }

    private fun mapJobResponseToJob(jobResponse: JobResponse): Job =
        Job(
            category = jobResponse.category?.let { mapJobCategoryResponseToJobsPagesInfo(it) },
            location = jobResponse.location?.let { mapJobLocationResponseToJobLocationInfo(it) },
            company = jobResponse.company?.let { mapJobCompanyResponseToJobCompanyInfo(it) },
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
                        getLocalDateTime(it).month.toString().lowercase(Locale.getDefault()) + " " +
                        getLocalDateTime(it).year.toString()
            },
            id = jobResponse.id,
            salaryMin = jobResponse.salaryMin,
            salary = jobResponse.salaryMin?.toInt()
                .toString() + " - " + jobResponse.salaryMax?.toInt().toString()
        )

    private fun mapJobCategoryResponseToJobsPagesInfo(jobCategoryResponse: JobCategoryResponse): JobCategory =
        JobCategory(
            label = jobCategoryResponse.label,
            tag = jobCategoryResponse.tag
        )

    private fun mapJobLocationResponseToJobLocationInfo(jobLocationResponse: JobLocationResponse): JobLocation =
        JobLocation(
            area = jobLocationResponse.area,
            displayName = jobLocationResponse.displayName
        )

    private fun mapJobCompanyResponseToJobCompanyInfo(jobCompanyResponse: JobCompanyResponse): JobCompany =
        JobCompany(
            displayName = jobCompanyResponse.displayName,
        )

    private fun getLocalDateTime(date: String): LocalDateTime {
        val pattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", russianLocale)
        return pattern.parse(date)!!.toInstant().atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}
