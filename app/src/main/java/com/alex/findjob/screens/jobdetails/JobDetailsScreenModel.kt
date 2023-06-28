package com.alex.findjob.screens.jobdetails

import android.util.Log
import cafe.adriel.voyager.hilt.ScreenModelFactory
import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.popNavigateEffect
import com.alex.findjob.screens.main.model.Job
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class JobDetailsScreenModel @AssistedInject constructor(
    @Assisted val jobItem: Job
) : BaseScreenModel<JobDetailsScreenState>(JobDetailsScreenState(jobItem)) {

    init {
        updateState { copy(data = jobItem) }
        Log.e("TAG", "data: $jobItem ", )
    }

    fun onBackClick() {
        launch {
            postSideEffect(popNavigateEffect())
        }
    }

    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(jobItem: Job): JobDetailsScreenModel
    }
}
