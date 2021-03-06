package com.worldsnas.sharedflow


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

actual fun suspendTest(body: suspend CoroutineScope.() -> Unit) {
    runBlocking { body() }
}