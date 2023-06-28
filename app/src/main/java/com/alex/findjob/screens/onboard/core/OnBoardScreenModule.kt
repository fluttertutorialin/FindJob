package com.alex.findjob.screens.onboard.core

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.alex.findjob.screens.main.core.MainInteractor
import com.alex.findjob.screens.main.core.MainInteractorImpl
import com.alex.findjob.screens.onboard.OnBoardScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class OnBoardScreenModule {
    @Binds
    @IntoMap
    @ScreenModelKey(OnBoardScreenModel::class)
    abstract fun bindOnBoardScreenModel(screenModel: OnBoardScreenModel): ScreenModel

}
