package com.alex.findjob.screens.splash.core

import com.alex.findjob.data.auth.AuthRepository
import javax.inject.Inject

class SplashInteractorImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : SplashInteractor {

    override suspend fun login(name: String) {
        authRepository.login(name)
    }

    override suspend fun getFirstRunStatus(): Boolean = authRepository.getFirstRunStatus()

}
