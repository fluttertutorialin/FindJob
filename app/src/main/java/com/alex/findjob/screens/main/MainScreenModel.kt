package com.alex.findjob.screens.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.coroutineScope
import com.alex.findjob.screens.common.BaseScreenModel
import com.alex.findjob.screens.common.pushNavigateEffect
import com.alex.findjob.screens.jobdetails.JobDetailsScreen
import com.alex.findjob.screens.main.core.MainInteractor
import com.alex.findjob.screens.main.model.JobModel
import kotlinx.coroutines.flow.Flow
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
                    getJobs(true)
                    interactor.saveFirstRunStatus(false)
                } catch (e: Exception) {
                    updateState { copy(isDataEmpty = true) }
                }
            } else {
                updateState { copy(searchModel = interactor.getLastSearchRequest()) }
                try {
                    getJobs(false)
                } catch (e: Exception) {
                    updateState { copy(isDataEmpty = true) }
                }
            }
        }
    }

    fun onItemClick(item: JobModel) {
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

    private fun getJobs(isRemote: Boolean) {
        val pagerData: Flow<PagingData<JobModel>> = Pager(
            pagingSourceFactory = { MainPaging(isRemote) },
            config = PagingConfig(pageSize = state.searchModel.resultsPerPage)
        ).flow.cachedIn(coroutineScope)
        updateState { copy(data = pagerData) }
    }

    fun onSearchClick() {
        launch {
            updateState { copy(showFilter = false, data = null, isDataEmpty = false) }
            getJobs(true)
        }
    }

    inner class MainPaging(private val isRemote: Boolean) : PagingSource<Int, JobModel>() {
        override fun getRefreshKey(state: PagingState<Int, JobModel>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobModel> {
            val page = params.key ?: START_INDEX
            val response = if (isRemote) interactor.getJobs(state.searchModel)
            else (interactor.getLastSearchResponse())
            if (response.results?.isEmpty() == true) updateState { copy(isDataEmpty = true) }
            return try {
                LoadResult.Page(
                    data = response.results ?: emptyList(),
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.results?.isEmpty() == true) null else page.plus(1),
                )
            } catch (e: Exception) {
                updateState { copy(isDataEmpty = true) }
                LoadResult.Error(e)
            }
        }
    }

}
