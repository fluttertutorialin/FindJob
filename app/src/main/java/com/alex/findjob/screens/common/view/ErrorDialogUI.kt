package com.alex.findjob.screens.common.view

import androidx.compose.runtime.Composable
import com.alex.findjob.localization.Vocabulary.localization

@Composable
fun ErrorDialogUI(
    errorMessage: String,
    onDismissAlertDialogClick: () -> Unit
) {
    CommonDialogUI(
        text = errorMessage.ifEmpty { localization.snackbarErrorDefault() },
        declineTitle = localization.ok(),
        onDismissAlertDialogClick = onDismissAlertDialogClick
    )
}
