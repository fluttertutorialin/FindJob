package com.alex.findjob.screens.main

import androidx.paging.PagingData
import com.alex.findjob.screens.main.model.JobModel
import com.alex.findjob.screens.main.model.SearchModel
import kotlinx.coroutines.flow.Flow

data class MainScreenState(
    val userName: String = "",
    val data: Flow<PagingData<JobModel>>? = null,
    val searchModel: SearchModel = SearchModel(),
    val showFilter: Boolean = false,
    val isDataEmpty: Boolean = false
)
