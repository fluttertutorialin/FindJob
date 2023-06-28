package com.alex.findjob.screens.main.core

import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel

interface MainInteractor {

    suspend fun getJobs(searchModel: SearchModel): Jobs

    suspend fun getUserName(): String

    suspend fun saveFirstRunStatus(status: Boolean)

    suspend fun getFirstRunStatus(): Boolean

    suspend fun getLastSearchResponse(): Jobs

    suspend fun getLastSearchRequest(): SearchModel

}
