package com.alex.findjob.screens.main.model

data class SearchModel(
    val countryTag: String = "gb",
    val searchTag: String = "",
    val locationTag: String = "",
    val fullTimeTag: Boolean = true,
    val partTimeTag: Boolean = false,
    val page: Int = 1,
    val resultsPerPage: Int = 20
)
