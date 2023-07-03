package com.alex.findjob.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.alex.findjob.di.firstRunKey
import com.alex.findjob.di.lastSearchRequest
import com.alex.findjob.di.lastSearchResponse
import com.alex.findjob.di.nameStoreKey
import com.alex.findjob.extensions.get
import com.alex.network.request.SearchRequest
import com.alex.network.response.jobs.JobsResponseForCache
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataSource {

    override suspend fun saveName(name: String) {
        dataStore.edit { it[nameStoreKey] = name }
    }

    override suspend fun getUserName(): String = dataStore.get(nameStoreKey, "")

    override suspend fun saveFirstRunStatus(status: Boolean) {
        dataStore.edit { it[firstRunKey] = status }
    }

    override suspend fun getFirstRunStatus(): Boolean = dataStore.get(firstRunKey, true)


    override suspend fun saveLastSearchResponse(jobsResponse: JobsResponseForCache) {
        dataStore.edit {
            it[lastSearchResponse] =
                Json.encodeToString(JobsResponseForCache.serializer(), jobsResponse)
        }
    }

    override suspend fun saveLastSearchRequest(searchModel: SearchRequest) {
        dataStore.edit {
            it[lastSearchRequest] = Json.encodeToString(SearchRequest.serializer(), searchModel)
        }
    }
//
//    override suspend fun getLastSearchResponse(): JobsResponse {
//        val jsonBuilder = Json { ignoreUnknownKeys = true }
//        return jsonBuilder.decodeFromString(
//            JobsResponse.serializer(), dataStore.get(
//                lastSearchResponse, ""
//            )
//        )
//    }

    override suspend fun getLastSearchResponse(): JobsResponseForCache {
        val jsonBuilder = Json { ignoreUnknownKeys = true }
        return jsonBuilder.decodeFromString(
            JobsResponseForCache.serializer(), dataStore.get(
                lastSearchResponse, ""
            )
        )
    }

    override suspend fun getLastSearchRequest(): SearchRequest {
        val jsonBuilder = Json { ignoreUnknownKeys = true }
        return jsonBuilder.decodeFromString(
            SearchRequest.serializer(), dataStore.get(
                lastSearchRequest, ""
            )
        )
    }

}
