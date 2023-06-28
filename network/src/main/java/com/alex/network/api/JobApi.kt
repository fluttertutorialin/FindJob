package com.alex.network.api

import com.alex.network.BuildConfig.APP_ID
import com.alex.network.BuildConfig.APP_KEY
import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class JobApi @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getJobs(request: SearchRequest): JobsResponse =
        client.get("${request.countryTag}/search/${request.page}")
        {
            parameter("app_id", APP_ID)
            parameter("app_key", APP_KEY)
            parameter("what", request.searchTag)
            parameter("where", request.locationTag)
            if (request.fullTimeTag) parameter("full_time", request.fullTimeTag.compareTo(false))
            if (request.partTimeTag) parameter("part_time", request.partTimeTag.compareTo(false))
        }.body()

}

