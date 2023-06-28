package com.alex.findjob.screens.main.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.alex.findjob.ui.theme.AppColors

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainFilterTextFieldUI(text: String, onValueChange: (String) -> Unit) {
    var inputText by rememberSaveable { mutableStateOf(text) }
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        value = inputText,
        onValueChange = {
            inputText = it
            if (it.isNotEmpty()) {
                onValueChange(it)
            }
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.Black
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
                        color = AppColors.MainBlueColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                if (inputText.isEmpty()) {
                    Text(
                        text = "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = AppColors.Black,
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
                innerTextField()
            }
        }
    )
}
