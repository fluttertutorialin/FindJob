package com.alex.findjob.screens.onboard

import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.replaceNavigateEffect
import com.alex.findjob.screens.main.MainScreen
import javax.inject.Inject

class OnBoardScreenModel @Inject constructor(
) : BaseScreenModel<OnBoardScreenState>(OnBoardScreenState()) {

    fun toMainScreen() {
        launch {
            postSideEffect(replaceNavigateEffect(MainScreen()))
        }
    }

}
