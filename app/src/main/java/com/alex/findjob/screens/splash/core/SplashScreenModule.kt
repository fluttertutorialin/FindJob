package com.alex.findjob.screens.splash.core

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.alex.findjob.screens.splash.SplashScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class SplashScreenModule {
    @Binds
    @IntoMap
    @ScreenModelKey(SplashScreenModel::class)
    abstract fun bindSplashScreenModel(screenModel: SplashScreenModel): ScreenModel

    @Binds
    abstract fun bindSplashInteractor(interactorImpl: SplashInteractorImpl): SplashInteractor

}
