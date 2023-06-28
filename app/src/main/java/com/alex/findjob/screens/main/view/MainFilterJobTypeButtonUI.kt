package com.alex.findjob.screens.main.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.ui.theme.AppColors

@Composable
fun MainFilterJobTypeButtonUI(text: String, onButtonClick: () -> Unit, isActive: Boolean) {
    TextButton(
        modifier = Modifier
            .wrapContentWidth()
            .border(
                width = 2.dp,
                color = if(isActive) AppColors.MainBlueColor else AppColors.GreyTextColor,
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = onButtonClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if(isActive) AppColors.MainBlueColor else AppColors.LightGreyColor,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            color = if(isActive) AppColors.White else AppColors.GreyTextColor,
            fontSize = 20.sp
        )
    }
}
