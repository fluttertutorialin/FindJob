package com.alex.findjob.screens.main.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.main.MainScreenState
import com.alex.findjob.ui.theme.AppColors

@Composable
fun MainScreenFilterUI(
    state: MainScreenState,
    onJobTagChange: (String) -> Unit,
    onLocationTagChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onChooseCountry: (String) -> Unit,
    onFullTimeClick: () -> Unit,
    onPartTimeClick: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 4.dp)
                    .border(
                        width = 1.dp,
                        color = AppColors.MainBlueColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                onClick = onSearchClick,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = AppColors.MainBlueColor,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = localization.search(),
                    color = AppColors.White,
                    fontSize = 20.sp
                )
            }
        }) {
        Box(Modifier.padding(it)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = localization.selectCountry(),
                    fontSize = 20.sp, color = AppColors.GreyTextColor
                )
                MainDropDownMenuUI(onChooseCountry, state.searchModel.countryTag)
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = localization.job(),
                    fontSize = 20.sp,
                    color = AppColors.GreyTextColor
                )
                MainFilterTextFieldUI(state.searchModel.searchTag, onJobTagChange)
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = localization.location(),
                    fontSize = 20.sp,
                    color = AppColors.GreyTextColor
                )
                MainFilterTextFieldUI(state.searchModel.locationTag, onLocationTagChange)
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = localization.jobType(),
                    fontSize = 20.sp,
                    color = AppColors.GreyTextColor
                )
                Row() {
                    MainFilterJobTypeButtonUI(
                        text = localization.fullTime(),
                        onFullTimeClick,
                        state.searchModel.fullTimeTag
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    MainFilterJobTypeButtonUI(
                        text = localization.partTime(),
                        onPartTimeClick,
                        state.searchModel.partTimeTag
                    )
                }
            }
        }
    }
}
