package com.alex.findjob.screens.onboard.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.screens.onboard.OnBoardItem
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardButtonAndIndicatorUI(
    item: OnBoardItem,
    pagerState: PagerState,
    toMainScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 72.dp)
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp),
            pagerState = pagerState,
            inactiveColor = AppColors.White.copy(alpha = 0.5f),
            activeColor = AppColors.White,
            indicatorWidth = 8.dp
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage != 2) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else toMainScreen()
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.White.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = null
        )
        {
            Text(
                text = item.buttonText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = item.buttonTextColor
            )
        }
    }
}