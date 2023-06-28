package com.alex.findjob.screens.jobdetails.core

import cafe.adriel.voyager.hilt.ScreenModelFactory
import cafe.adriel.voyager.hilt.ScreenModelFactoryKey
import com.alex.findjob.screens.jobdetails.JobDetailsScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class JobDetailsScreenModule {

    @Binds
    @IntoMap
    @ScreenModelFactoryKey(JobDetailsScreenModel.Factory::class)
    abstract fun bindScreenModel(screenModel: JobDetailsScreenModel.Factory): ScreenModelFactory

}
