package com.alex.findjob.screens.main

import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.MessageSideEffect
import com.alex.findjob.screens.common.pushNavigateEffect
import com.alex.findjob.screens.jobdetails.JobDetailsScreen
import com.alex.findjob.screens.main.core.MainInteractor
import com.alex.findjob.screens.main.model.Job
import javax.inject.Inject

class MainScreenModel @Inject constructor(
    private val interactor: MainInteractor,
) : BaseScreenModel<MainScreenState>(MainScreenState()) {

    init {
        getData()
    }

    private fun getData() {
        launch {
            updateState { copy(userName = interactor.getUserName()) }
            if (interactor.getFirstRunStatus()) {
                try {
                    updateState {
                        copy(data = interactor.getJobs(state.searchModel))
                    }
                    interactor.saveFirstRunStatus(false)
                } catch (e: Exception) {
                    postSideEffect(MessageSideEffect())
                }
            } else updateState {
                copy(
                    data = interactor.getLastSearchResponse(),
                    searchModel = interactor.getLastSearchRequest()
                )
            }
        }
    }

    fun onItemClick(item: Job) {
        launch {
            postSideEffect(pushNavigateEffect(JobDetailsScreen(item)))
        }
    }

    fun onFilterClick() {
        if (state.showFilter) updateState { copy(showFilter = false) }
        else updateState { copy(showFilter = true) }
    }

    fun onTagChange(searchTag: String) {
        updateState { copy(searchModel = searchModel.copy(searchTag = searchTag)) }
    }

    fun onLocationTagChange(locationTag: String) {
        updateState { copy(searchModel = searchModel.copy(locationTag = locationTag)) }
    }

    fun onChooseCountry(countryTag: String) {
        updateState { copy(searchModel = searchModel.copy(countryTag = countryTag)) }
    }

    fun onFullTimeClick() {
        if (state.searchModel.fullTimeTag) updateState {
            copy(searchModel = searchModel.copy(fullTimeTag = false, partTimeTag = true))
        }
        else updateState {
            copy(searchModel = searchModel.copy(fullTimeTag = true, partTimeTag = false))
        }
    }

    fun onPartTimeClick() {
        if (state.searchModel.partTimeTag) updateState {
            copy(searchModel = searchModel.copy(partTimeTag = false, fullTimeTag = true))
        }
        else updateState {
            copy(searchModel = searchModel.copy(partTimeTag = true, fullTimeTag = false))
        }
    }

    fun onSearchClick() {
        launch {
            updateState { copy(showFilter = false, data = null, showDataIsEmpty = false) }
            try {
                if (interactor.getJobs(state.searchModel).results?.isNotEmpty() == true) {
                    updateState { copy(data = interactor.getJobs(state.searchModel)) }
                } else updateState { copy(showDataIsEmpty = true) }
            } catch (e: Exception) {
                postSideEffect(MessageSideEffect())
            }
        }
    }

}
