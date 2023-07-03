package com.alex.findjob.screens.main.core

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.alex.findjob.screens.main.MainScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class MainScreenModule {

    @Binds
    @IntoMap
    @ScreenModelKey(MainScreenModel::class)
    abstract fun bindMainScreenModel(screenModel: MainScreenModel): ScreenModel

    @Binds
    abstract fun bindMainInteractor(interactorImpl: MainInteractorImpl): MainInteractor
}