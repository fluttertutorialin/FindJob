package com.alex.findjob.screens.main.core

import com.alex.findjob.data.auth.AuthRepository
import com.alex.findjob.data.repository.JobsRepository
import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel
import javax.inject.Inject

class MainInteractorImpl @Inject constructor(
    private val repository: JobsRepository,
    private val authRepository: AuthRepository
) : MainInteractor {

    override suspend fun getJobs(searchModel: SearchModel): Jobs =
        repository.getJobs(searchModel)

    override suspend fun getUserName(): String = authRepository.getUserName()

    override suspend fun saveFirstRunStatus(status: Boolean) {
        authRepository.saveFirstRunStatus(status)
    }

    override suspend fun getFirstRunStatus(): Boolean = authRepository.getFirstRunStatus()

    override suspend fun getLastSearchResponse(): Jobs = repository.getLastSearchResponse()

    override suspend fun getLastSearchRequest(): SearchModel = repository.getLastSearchRequest()

}
