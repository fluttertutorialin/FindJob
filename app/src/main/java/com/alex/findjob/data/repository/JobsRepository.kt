package com.alex.findjob.data.repository

import com.alex.findjob.screens.main.model.JobsModel
import com.alex.findjob.screens.main.model.SearchModel

interface JobsRepository {

    suspend fun getJobs(searchModel: SearchModel): JobsModel

    suspend fun getLastSearchResponse(): JobsModel

    suspend fun getLastSearchRequest(): SearchModel

}
