package com.alex.findjob.screens.common.model

sealed interface Message
object DefaultErrorMessage : Message
object UnknownHostMessage : Message
class UnknownExceptionMessage(val text: String) : Message
