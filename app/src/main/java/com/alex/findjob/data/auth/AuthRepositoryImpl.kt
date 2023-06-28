package com.alex.findjob.data.auth

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
) : AuthRepository {

    override suspend fun login(name: String) {
        dataSource.saveName(name)
    }

    override suspend fun getUserName(): String = dataSource.getUserName()

    override suspend fun saveFirstRunStatus(status: Boolean) {
        dataSource.saveFirstRunStatus(status)
    }

    override suspend fun getFirstRunStatus(): Boolean = dataSource.getFirstRunStatus()

}
