package com.localhost.taxiapp.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun runOnIO(func: suspend () -> Unit) {
    withContext(Dispatchers.IO) {
        func()
    }
}

fun <T> CoroutineScope.launchCatching(
    func: suspend () -> T,
    onSuccess:  (T) -> Unit,
    onError:  (Throwable) -> Unit
) {
    launch {
        runCatching {
            withContext(Dispatchers.IO) {
                func()
            }
        }.onSuccess { onSuccess(it) }.onFailure { onError(it) }
    }
}