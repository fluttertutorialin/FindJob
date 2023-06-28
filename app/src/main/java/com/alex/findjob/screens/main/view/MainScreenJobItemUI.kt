package com.alex.findjob.screens.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.screens.main.model.Job
import com.alex.findjob.ui.theme.AppColors

@Composable
fun MainScreenJobItemUi(item: Job, onItemClick: (Job) -> Unit) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = AppColors.DividerColor)
            .background(AppColors.White)
            .clickable { onItemClick(item) }
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item.title?.let { Text(text = it, fontSize = 22.sp, fontWeight = FontWeight.Bold) }
            item.company?.displayName?.let { Text(text = it) }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = AppColors.DividerColor,
                thickness = 2.dp
            )
            item.location?.displayName?.let { Text(text = it) }
            item.salary?.let { Text(text = it, color = AppColors.MainBlueColor) }
        }
    }
}