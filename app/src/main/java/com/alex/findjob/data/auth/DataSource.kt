package com.alex.findjob.data.auth

import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobsResponse

interface DataSource {

    suspend fun saveName(name: String)

    suspend fun getUserName(): String

    suspend fun saveFirstRunStatus(status: Boolean)

    suspend fun getFirstRunStatus(): Boolean

    suspend fun saveLastSearchResponse(jobsResponse: JobsResponse)

    suspend fun getLastSearchResponse(): JobsResponse

    suspend fun saveLastSearchRequest(searchModel: SearchRequest)

    suspend fun getLastSearchRequest(): SearchRequest

}
