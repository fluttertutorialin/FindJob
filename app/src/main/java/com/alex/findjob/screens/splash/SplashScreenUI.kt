package com.alex.findjob.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.view.TransparentStatusBarUI
import com.alex.findjob.screens.splash.view.enterYourName
import com.alex.findjob.screens.splash.view.next
import com.alex.findjob.ui.theme.AppColors
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SplashScreenUI(
    state: SplashScreenState,
    login: () -> Unit,
    onNameChange: (String) -> Unit
) {
    TransparentStatusBarUI()
    Box(
        Modifier
            .fillMaxSize()
            .background(color = AppColors.MainBlueColor),
        contentAlignment = Alignment.Center
    ) {
        if (state.showNameInput)
            Column(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsWithImePadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var inputText by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current
                BasicTextField(modifier = Modifier.padding(horizontal = 40.dp),
                    value = inputText,
                    onValueChange = {
                        inputText = it
                        if (it.isNotEmpty()) {
                            onNameChange(it)
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = AppColors.White
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = AppColors.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 16.dp) // inner padding
                        ) {
                            if (inputText.isEmpty()) {
                                Text(
                                    text = localization.enterYourName(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = AppColors.GreyTextColor,
                                    modifier = Modifier.align(Alignment.BottomStart)
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                        .border(
                            width = 1.dp,
                            color = AppColors.MainBlueColor,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    onClick = login,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor =
                        if (inputText.isEmpty()) AppColors.White.copy(alpha = 0.5f) else AppColors.White,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    enabled = inputText.isNotEmpty()
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = localization.next(),
                        color = if (inputText.isEmpty()) AppColors.White else AppColors.MainBlueColor,
                        fontSize = 16.sp
                    )
                }
            }
    }
}
