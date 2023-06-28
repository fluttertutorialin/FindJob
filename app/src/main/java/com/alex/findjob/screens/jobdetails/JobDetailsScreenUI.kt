package com.alex.findjob.screens.jobdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.view.TopBarRowUI
import com.alex.findjob.screens.jobdetails.view.description
import com.alex.findjob.screens.jobdetails.view.location
import com.alex.findjob.screens.jobdetails.view.postedOn
import com.alex.findjob.screens.jobdetails.view.salary
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun JobDetailsScreenUI(
    state: JobDetailsScreenState,
    onBackClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsWithImePadding()
            .verticalScroll(rememberScrollState())
    ) {
        TopBarRowUI(
            backArrow = true,
            onBackClick = onBackClick,
            text = "",
            rightImage = null,
            onClickRightImage = null
        )
        Column(Modifier.padding(horizontal = 16.dp)) {
            state.data.title?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            state.data.company?.displayName?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.GreyTextColor
                )
            }
            state.data.created?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = localization.postedOn() + it,
                    fontSize = 16.sp,
                    color = AppColors.GreyTextColor
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                color = AppColors.DividerColor,
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = localization.salary(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            state.data.salary?.let { Text(modifier = Modifier.padding(top = 4.dp), text = it) }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                color = AppColors.DividerColor,
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = localization.location(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            state.data.location?.displayName?.let { Text(modifier = Modifier.padding(top = 4.dp), text = it) }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                color = AppColors.DividerColor,
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = localization.description(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            state.data.description?.let { Text(modifier = Modifier.padding(top = 4.dp), text = it) }
            state.data.redirectUrl?.let { Text(modifier = Modifier.padding(top = 4.dp), text = it) }
        }
    }
}
