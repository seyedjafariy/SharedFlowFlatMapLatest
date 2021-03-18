package com.worldsnas.sharedflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*

actual fun suspendTest(body: suspend CoroutineScope.() -> Unit) {
    val dispatcher = TestCoroutineDispatcher()
    runBlockingTest(dispatcher) {
        Dispatchers.setMain(dispatcher)
        body()
        Dispatchers.resetMain()
    }
}