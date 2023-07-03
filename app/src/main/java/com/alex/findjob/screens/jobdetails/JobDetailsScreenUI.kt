package com.alex.findjob.screens.jobdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.view.TopBarRowUI
import com.alex.findjob.screens.jobdetails.view.JobDetailsSectionUI
import com.alex.findjob.screens.jobdetails.view.description
import com.alex.findjob.screens.jobdetails.view.link
import com.alex.findjob.screens.jobdetails.view.location
import com.alex.findjob.screens.jobdetails.view.postedOn
import com.alex.findjob.screens.jobdetails.view.salary
import com.alex.findjob.screens.main.view.salaryIsNotSpecified
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun JobDetailsScreenUI(
    state: JobDetailsScreenState,
    onBackClick: () -> Unit
) {
    val data = state.data
    val uriHandler = LocalUriHandler.current
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
            data.title?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            data.company?.displayName?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.GreyTextColor
                )
            }
            data.created?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = localization.postedOn() + it,
                    fontSize = 16.sp,
                    color = AppColors.GreyTextColor
                )
            }
            JobDetailsSectionUI(
                localization.salary(),
                if (data.salaryIsPredicted == 0) localization.salaryIsNotSpecified()
                else data.salary,
                false, uriHandler
            )
            JobDetailsSectionUI(localization.location(), data.location?.displayName, false, uriHandler)
            JobDetailsSectionUI(localization.description(), data.description, false, uriHandler)
            JobDetailsSectionUI(localization.link(), data.redirectUrl, true, uriHandler)
        }
    }
}
