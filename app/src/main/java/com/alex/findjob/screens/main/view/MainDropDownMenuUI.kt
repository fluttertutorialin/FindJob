package com.alex.findjob.screens.main.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.findjob.R
import com.alex.findjob.screens.main.countries
import com.alex.findjob.ui.theme.AppColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainDropDownMenuUI(onChooseCountry: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(countries["gb"]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        selectedItem?.let {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(
                        width = 2.dp,
                        color = AppColors.MainBlueColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                readOnly = true,
                value = it, //TODO взять из state прошлого запроса?
                onValueChange = {},
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_feedback_dropdown),
                            contentDescription = ""
                        )
                    }
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = AppColors.LightGreyColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = AppColors.Black
                ),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(fontSize = 20.sp)
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countries.forEach {
                DropdownMenuItem(onClick = {
                    onChooseCountry(it.key)
                    selectedItem = it.value
                    expanded = false
                }) { Text(text = it.value) }
            }
        }
    }
}
