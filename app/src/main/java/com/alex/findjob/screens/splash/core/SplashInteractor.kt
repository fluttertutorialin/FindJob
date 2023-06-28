package com.alex.findjob.screens.splash.core

interface SplashInteractor {

    suspend fun login(name: String)

    suspend fun getFirstRunStatus(): Boolean

}
