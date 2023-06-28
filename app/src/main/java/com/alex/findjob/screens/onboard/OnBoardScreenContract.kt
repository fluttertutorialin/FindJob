package com.alex.findjob.screens.onboard

import androidx.compose.ui.graphics.Color

data class OnBoardScreenState(
    val isLoading: Boolean = false
)

data class OnBoardItem(
    val text: String,
    val textColor: Color,
    val background: Color,
    val buttonText: String,
    val buttonTextColor: Color,
    val image: Int
)
