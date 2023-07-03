package com.alex.findjob.screens.jobdetails

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
import com.alex.findjob.screens.main.model.JobModel
import kotlinx.coroutines.flow.filterIsInstance

class JobDetailsScreen(private val jobModelItem: JobModel) : Screen {
    @Composable
    override fun Content() {
        val screenModel: JobDetailsScreenModel =
            getScreenModel<JobDetailsScreenModel, JobDetailsScreenModel.Factory> { factory ->
                factory.create(jobModelItem)
            }
        val state by screenModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.current

        screenModel.sideEffectFlow.filterIsInstance<NavigateSideEffect>().collectOnLaunch {
            navigator?.handleSideEffect(it)
        }

        JobDetailsScreenUI(
            state = state,
            onBackClick = screenModel::onBackClick
        )

        MessageHost(screenModel)
    }
}
