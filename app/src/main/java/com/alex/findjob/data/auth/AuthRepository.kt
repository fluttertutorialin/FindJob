package com.alex.findjob.data.auth


interface AuthRepository {

    suspend fun login(name: String)

    suspend fun getUserName(): String

    suspend fun saveFirstRunStatus(status: Boolean)

    suspend fun getFirstRunStatus(): Boolean

}