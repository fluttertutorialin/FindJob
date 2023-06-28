package com.alex.findjob.screens.splash

import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.replaceNavigateEffect
import com.alex.findjob.screens.main.MainScreen
import com.alex.findjob.screens.onboard.OnBoardScreen
import com.alex.findjob.screens.splash.core.SplashInteractor
import javax.inject.Inject

class SplashScreenModel @Inject constructor(
    private val interactor: SplashInteractor
) : BaseScreenModel<SplashScreenState>(SplashScreenState()) {

    init {
        checkFirstRun()
    }

    private fun checkFirstRun() {
        launch {
            if (!interactor.getFirstRunStatus()) postSideEffect(replaceNavigateEffect(MainScreen()))
            else {
                updateState { copy(showNameInput = true) }
            }
        }
    }

    fun onNameChange(name: String) {
        updateState { copy(inputName = name) }
    }

    fun login() {
        launch {
            interactor.login(state.inputName)
            postSideEffect(replaceNavigateEffect(OnBoardScreen()))
        }
    }

}
