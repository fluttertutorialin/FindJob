package com.alex.findjob.screens.main.core

import com.alex.findjob.screens.main.model.JobsModel
import com.alex.findjob.screens.main.model.SearchModel

interface MainInteractor {

    suspend fun getJobs(searchModel: SearchModel): JobsModel

    suspend fun getUserName(): String

    suspend fun saveFirstRunStatus(status: Boolean)

    suspend fun getFirstRunStatus(): Boolean

    suspend fun getLastSearchResponse(): JobsModel

    suspend fun getLastSearchRequest(): SearchModel

}
