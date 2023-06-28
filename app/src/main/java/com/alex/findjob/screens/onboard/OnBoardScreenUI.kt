package com.alex.findjob.screens.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alex.findjob.R
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.view.TransparentStatusBarUI
import com.alex.findjob.screens.onboard.view.OnboardButtonAndIndicatorUI
import com.alex.findjob.screens.onboard.view.OnboardPagerScreenUI
import com.alex.findjob.screens.onboard.view.finish
import com.alex.findjob.screens.onboard.view.next
import com.alex.findjob.screens.onboard.view.onBoard1MainText
import com.alex.findjob.screens.onboard.view.onBoard2MainText
import com.alex.findjob.screens.onboard.view.onBoard3MainText
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardScreenUI(
    toMainScreen: () -> Unit
) {
    val pagerState = rememberPagerState()
    val items = listOf(
        OnBoardItem(
            text = localization.onBoard1MainText(),
            textColor = AppColors.White,
            background = AppColors.Onboard1Background,
            buttonTextColor = AppColors.ButtonOnboard1Text,
            buttonText = localization.next(),
            image = R.drawable.onboard_1
        ),
        OnBoardItem(
            text = localization.onBoard2MainText(),
            textColor = AppColors.White,
            background = AppColors.Onboard2Background,
            buttonTextColor = AppColors.ButtonOnboard2Text,
            buttonText = localization.next(),
            image = R.drawable.onboard_2
        ),
        OnBoardItem(
            text = localization.onBoard3MainText(),
            textColor = AppColors.Black,
            background = AppColors.Onboard3Background,
            buttonTextColor = AppColors.ButtonOnboard3Text,
            buttonText = localization.finish(),
            image = R.drawable.onboard_3
        )
    )

    TransparentStatusBarUI()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                count = 3,
                state = pagerState,
            ) { position ->
                OnboardPagerScreenUI(items[position])
            }
        }
        OnboardButtonAndIndicatorUI(
            items[pagerState.currentPage],
            pagerState,
            toMainScreen
        )
    }
}
