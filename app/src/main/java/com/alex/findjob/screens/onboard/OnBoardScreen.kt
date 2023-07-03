package com.alex.findjob.screens.onboard

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.alex.findjob.extensions.collectOnLaunch
import com.alex.findjob.screens.common.MessageHost
import com.alex.findjob.screens.common.NavigateSideEffect
import com.alex.findjob.screens.common.handleSideEffect
import kotlinx.coroutines.flow.filterIsInstance

class OnBoardScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: OnBoardScreenModel = getScreenModel()
        val navigator = LocalNavigator.current

        screenModel.sideEffectFlow.filterIsInstance<NavigateSideEffect>().collectOnLaunch {
            navigator?.handleSideEffect(it)
        }
        OnBoardScreenUI(
            toMainScreen = screenModel::toMainScreen
        )
        MessageHost(screenModel)
    }
}
