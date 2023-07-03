package com.alex.findjob.screens.jobdetails.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.ui.theme.AppColors

@Composable
fun JobDetailsSectionUI(title: String, description: String?, isLink: Boolean, uriHandler: UriHandler) {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        color = AppColors.DividerColor,
        thickness = 2.dp
    )
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
    if (isLink) {
        description?.let {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { uriHandler.openUri(it) },
                text = it, color = AppColors.MainBlueColor
            )
        }
    } else description?.let { Text(modifier = Modifier.padding(top = 4.dp), text = it) }
}
