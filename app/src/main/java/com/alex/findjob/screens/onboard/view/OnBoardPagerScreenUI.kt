package com.alex.findjob.screens.onboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.screens.onboard.OnBoardItem

@Composable
fun OnboardPagerScreenUI(item: OnBoardItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = item.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            text = item.text, fontWeight = FontWeight.Bold,
            color = item.textColor,
            textAlign = TextAlign.Center,
            fontSize = 40.sp
        )
    }
}
