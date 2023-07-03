package com.alex.findjob.data.repository

import com.alex.findjob.data.auth.DataSource
import com.alex.findjob.di.IoDispatcher
import com.alex.findjob.screens.main.model.JobsModel
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
    private val cacheMapper: CacheMapper,
    private val dataSource: DataSource
) : JobsRepository {

    override suspend fun getJobs(searchModel: SearchModel): JobsModel = withContext(ioDispatcher) {
        val request = cacheMapper.mapSearchModelToSearchRequest(searchModel)
        val response = jobApi.getJobs(request)
        val responseCache = cacheMapper.mapJobsResponseToJobsForCache(response, searchModel.countryTag)
        dataSource.saveLastSearchRequest(request)
        dataSource.saveLastSearchResponse(responseCache)
        return@withContext mapper.mapJobsResponseToJobs(responseCache)
    }

    override suspend fun getLastSearchResponse(): JobsModel =
        mapper.mapJobsResponseToJobs(dataSource.getLastSearchResponse())

    override suspend fun getLastSearchRequest(): SearchModel =
        mapper.mapSearchRequestToSearchModel(dataSource.getLastSearchRequest())

}
