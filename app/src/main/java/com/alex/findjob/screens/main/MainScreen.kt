package com.alex.findjob.screens.main

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

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: MainScreenModel = getScreenModel()
        val state by screenModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.current

        screenModel.sideEffectFlow.filterIsInstance<NavigateSideEffect>().collectOnLaunch {
            navigator?.handleSideEffect(it)
        }

        MainScreenUI(
            state = state,
            onItemClick = screenModel::onItemClick,
            onFilterClick = screenModel::onFilterClick,
            onSearchClick = screenModel::onSearchClick,
            onJobTagChange = screenModel::onTagChange,
            onLocationTagChange = screenModel::onLocationTagChange,
            onChooseCountry = screenModel::onChooseCountry,
            onFullTimeClick = screenModel::onFullTimeClick,
            onPartTimeClick = screenModel::onPartTimeClick,
        )

        MessageHost(screenModel)
    }
}
