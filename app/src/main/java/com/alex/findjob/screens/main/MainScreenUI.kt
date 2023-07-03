package com.alex.findjob.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.alex.findjob.R
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.view.TopBarRowUI
import com.alex.findjob.screens.common.view.TransparentBlackStatusBarUI
import com.alex.findjob.screens.main.model.JobModel
import com.alex.findjob.screens.main.view.MainBackPressHandler
import com.alex.findjob.screens.main.view.MainScreenFilterUI
import com.alex.findjob.screens.main.view.MainScreenJobItemUi
import com.alex.findjob.screens.main.view.hello
import com.alex.findjob.screens.main.view.nothingHere
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun MainScreenUI(
    state: MainScreenState,
    onItemClick: (JobModel) -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onJobTagChange: (String) -> Unit,
    onLocationTagChange: (String) -> Unit,
    onChooseCountry: (String) -> Unit,
    onFullTimeClick: () -> Unit,
    onPartTimeClick: () -> Unit
) {
    val jobs = state.data?.collectAsLazyPagingItems()
    TransparentBlackStatusBarUI()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.LightGreyColor)
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarRowUI(
            backArrow = false,
            onBackClick = {},
            text = localization.hello() + state.userName,
            rightImage = R.drawable.ic_filter,
            onClickRightImage = onFilterClick
        )
        Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    jobs?.let {
                        items(items = jobs) { jobItem ->
                            jobItem?.let { it1 -> MainScreenJobItemUi(it1, onItemClick) }
                        }
                    }
                }
            if (jobs?.loadState?.refresh is LoadState.Loading) Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = AppColors.MainBlueColor) }

            if (state.isDataEmpty) Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.LightGreyColor),
                contentAlignment = Alignment.Center
            ) { Text(text = localization.nothingHere()) }

            if (state.showFilter) Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.LightGreyColor)
            ) {
                MainScreenFilterUI(
                    state,
                    onJobTagChange,
                    onLocationTagChange,
                    onSearchClick,
                    onChooseCountry,
                    onFullTimeClick,
                    onPartTimeClick
                )
            }
        }
    }
    MainBackPressHandler(state, onBackPressed = onFilterClick)
}
