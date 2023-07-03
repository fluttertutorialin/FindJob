package com.alex.findjob.screens.jobdetails

import cafe.adriel.voyager.hilt.ScreenModelFactory
import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.popNavigateEffect
import com.alex.findjob.screens.main.model.JobModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class JobDetailsScreenModel @AssistedInject constructor(
    @Assisted val jobModelItem: JobModel
) : BaseScreenModel<JobDetailsScreenState>(JobDetailsScreenState(jobModelItem)) {

    init {
        updateState { copy(data = jobModelItem) }
    }

    fun onBackClick() {
        launch {
            postSideEffect(popNavigateEffect())
        }
    }

    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(jobModelItem: JobModel): JobDetailsScreenModel
    }
}
