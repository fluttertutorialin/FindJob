package com.alex.findjob.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.alex.findjob.extensions.collectOnLaunch
import com.alex.findjob.screens.common.MessageHost
import com.alex.findjob.screens.common.NavigateSideEffect
import com.alex.findjob.screens.common.handleSideEffect
import kotlinx.coroutines.flow.filterIsInstance

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: SplashScreenModel = getScreenModel()
        val state by screenModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.current

        screenModel.sideEffectFlow.filterIsInstance<NavigateSideEffect>().collectOnLaunch {
            navigator?.handleSideEffect(it)
        }
        SplashScreenUI(
            state = state,
            login = screenModel::login,
            onNameChange = screenModel::onNameChange
        )
        MessageHost(screenModel)
    }
}
