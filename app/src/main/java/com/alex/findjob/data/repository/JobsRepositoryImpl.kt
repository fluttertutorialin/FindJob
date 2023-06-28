package com.alex.findjob.data.repository

import com.alex.findjob.data.auth.DataSource
import com.alex.findjob.di.IoDispatcher
import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel
import com.alex.network.api.JobApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val jobApi: JobApi,
    private val mapper: JobsMapper,
    private val dataSource: DataSource
) : JobsRepository {

    override suspend fun getJobs(searchModel: SearchModel): Jobs = withContext(ioDispatcher) {
        val response = jobApi.getJobs(mapper.mapSearchModelToSearchRequest(searchModel))
        dataSource.saveLastSearchRequest(mapper.mapSearchModelToSearchRequest(searchModel))
        dataSource.saveLastSearchResponse(response)
        return@withContext mapper.mapJobsResponseToJobs(response)
    }

    override suspend fun getLastSearchResponse(): Jobs =
        mapper.mapJobsResponseToJobs(dataSource.getLastSearchResponse())

    override suspend fun getLastSearchRequest(): SearchModel =
        mapper.mapSearchRequestToSearchModel(dataSource.getLastSearchRequest())

}
