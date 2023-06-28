package com.alex.findjob.screens.common.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.alex.findjob.localization.Localization
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.ui.theme.AppColors

@Composable
fun CommonDialogUI(
    title: String? = null,
    text: String,
    confirmTitle: String? = null,
    declineTitle: String? = null,
    onDismissAlertDialogClick: () -> Unit,
    onConfirmAlertDialogClick: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = onDismissAlertDialogClick,
        dismissButton = {
            TextButton(onClick = onDismissAlertDialogClick) {
                Text(
                    text = (declineTitle ?: localization.cancel()).uppercase(),
                    style = MaterialTheme.typography.h4.copy(
                        color = AppColors.Accent200,
                        fontSize = 14.sp
                    )
                )
            }
        },
        confirmButton = {
            if (onConfirmAlertDialogClick != null) {
                TextButton(onClick = onConfirmAlertDialogClick) {
                    Text(
                        text = (confirmTitle ?: localization.ok()).uppercase(),
                        style = MaterialTheme.typography.h4.copy(
                            color = AppColors.Accent200,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        },
        title = if (title != null) {
            {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp
                    )
                )
            }
        } else null,
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp
                )
            )
        }
    )
}

@Composable
fun CommonDialogUI(
    title: (Localization.() -> String)? = null,
    text: (Localization.() -> String),
    confirmTitle: (Localization.() -> String)? = null,
    declineTitle: (Localization.() -> String)? = null,
    onDismissAlertDialogClick: () -> Unit,
    onConfirmAlertDialogClick: (() -> Unit)? = null
) = CommonDialogUI(
    title = title?.let { localization.it() },
    text = localization.text(),
    confirmTitle = confirmTitle?.let { localization.it() },
    declineTitle = declineTitle?.let { localization.it() },
    onConfirmAlertDialogClick = onConfirmAlertDialogClick,
    onDismissAlertDialogClick = onDismissAlertDialogClick
)
