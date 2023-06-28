package com.alex.findjob.screens.common.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.R
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentStatusBarUI() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    }
}

@Composable
fun TransparentBlackStatusBarUI() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
    }
}

@Composable
fun TopBarRowUI(
    backArrow: Boolean,
    onBackClick: () -> Unit,
    text: String,
    rightImage: Int?,
    onClickRightImage: (() -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        if (backArrow) IconButton(
            modifier = Modifier
                .size(48.dp)
                .padding(start = 4.dp), onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "",
                Modifier.size(28.dp),
                tint = Color.Unspecified
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = text,
            color = AppColors.Black,
            fontSize = 22.sp
        )
        IconButton(
            modifier = Modifier
                .size(48.dp),
            onClick = { onClickRightImage?.let { it() } }
        ) {
            rightImage?.let { painterResource(it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = "",
                    Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}
