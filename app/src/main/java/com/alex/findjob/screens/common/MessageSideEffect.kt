package com.alex.findjob.screens.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.alex.findjob.extensions.collectOnLaunch
import com.alex.findjob.localization.Vocabulary.localization
import com.alex.findjob.screens.common.model.Message
import com.alex.findjob.screens.common.model.UnknownExceptionMessage
import com.alex.findjob.screens.common.model.UnknownHostMessage
import com.alex.findjob.screens.common.view.ErrorDialogUI
import com.alex.findjob.screens.common.view.errorUnknownHost
import com.alex.findjob.screens.common.view.snackbarErrorDefault
import kotlinx.coroutines.flow.filterIsInstance

class MessageSideEffect(val message: Message? = null) : BaseSideEffect

@Composable
fun MessageHost(screenModel: BaseScreenModel<*>, onDismiss: () -> Unit = {}) {
    val activeMessage = remember {
        mutableStateOf<Message?>(null)
    }

    screenModel.sideEffectFlow.filterIsInstance<MessageSideEffect>().collectOnLaunch {
        if (activeMessage.value == null) {
            activeMessage.value = it.message
        }
    }

    val messageValue = activeMessage.value
    if (messageValue != null) {
        ErrorDialogUI(
            errorMessage = when(messageValue) {
                is UnknownHostMessage -> localization.errorUnknownHost()
                is UnknownExceptionMessage -> messageValue.text
                else -> localization.snackbarErrorDefault()
            },
            onDismissAlertDialogClick = {
                onDismiss()
                activeMessage.value = null
            }
        )
    }
}
