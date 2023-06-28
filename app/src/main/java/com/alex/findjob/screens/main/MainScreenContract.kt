package com.alex.findjob.screens.main

import com.alex.findjob.screens.main.model.Jobs
import com.alex.findjob.screens.main.model.SearchModel

data class MainScreenState(
    val userName: String = "",
    val data: Jobs? = null,
    val searchModel: SearchModel = SearchModel(),
    val showFilter: Boolean = false,
    val showDataIsEmpty: Boolean = false
)

internal val countries =
    hashMapOf(
        "gb" to "Great Britain",
        "us" to "USA",
        "ru" to "Russia",
        "pl" to "Poland",
        "br" to "Brazil",
        "sp" to "Spain",
        "de" to "Germany",
        "cz" to "Czech",
        "au" to "Australia",
        "fr" to "France"
    )
