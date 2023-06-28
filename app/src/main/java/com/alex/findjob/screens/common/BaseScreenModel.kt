package com.alex.findjob.screens.common

import cafe.adriel.voyager.core.model.ScreenModel
import com.alex.findjob.screens.common.model.DefaultErrorMessage
import com.alex.findjob.screens.common.model.UnknownExceptionMessage
import com.alex.findjob.screens.common.model.UnknownHostMessage
import io.github.aakira.napier.Napier
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseScreenModel<STATE>(initialState: STATE) : ScreenModel, CoroutineScope {
    protected val mutableStateFlow: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val stateFlow: StateFlow<STATE> = mutableStateFlow.asStateFlow()
    val state: STATE get() = stateFlow.value

    private val mutableSideEffectFlow: MutableSharedFlow<BaseSideEffect> by lazy {
        MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
            replay = 0
        )
    }

    val sideEffectFlow: SharedFlow<BaseSideEffect> = mutableSideEffectFlow.asSharedFlow()

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    val silentExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    protected inline fun updateState(transform: STATE.() -> STATE) {
        mutableStateFlow.value = transform.invoke(state)
    }

    suspend fun postSideEffect(sideEffect: BaseSideEffect) {
        mutableSideEffectFlow.emit(sideEffect)
    }

    protected fun launch(
        context: CoroutineContext = defaultExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job = this@BaseScreenModel.launch(
        context = context,
        start = CoroutineStart.DEFAULT
    ) {
        block(this)
    }

    private fun onError(throwable: Throwable) {
        if (throwable !is CancellationException) {
            Napier.e(throwable.message.orEmpty(), throwable)
            mutableSideEffectFlow.tryEmit(
                MessageSideEffect(
                    when(throwable) {
                        is UnknownHostException -> UnknownHostMessage
                        !is SocketTimeoutException -> UnknownExceptionMessage(throwable.message ?: "")
                        else -> DefaultErrorMessage
                    }
                )
            )
        }
    }
}
