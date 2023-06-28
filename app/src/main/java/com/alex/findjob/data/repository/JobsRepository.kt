package com.alex.findjob.data.repository

import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel

interface JobsRepository {

    suspend fun getJobs(searchModel: SearchModel): Jobs

    suspend fun getLastSearchResponse(): Jobs

    suspend fun getLastSearchRequest(): SearchModel

}
