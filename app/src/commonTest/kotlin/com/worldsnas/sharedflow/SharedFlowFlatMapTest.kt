package com.worldsnas.sharedflow

import app.cash.turbine.test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SharedFlowFlatMapTest {

    @Test
    fun receiveElements() = suspendTest {
        flowOf(1, 2, 3)
            .map {
                it.toString()
            }
            .test {
                assertEquals("1", expectItem())
                assertEquals("2", expectItem())
                assertEquals("3", expectItem())
                expectComplete()
            }
    }

    @Test
    fun receiveFlatMappedElements() = suspendTest {
        flowOf(1, 2, 3)
            .flatMapLatest {
                flowOf(it.toString())
            }
            .test {
                assertEquals("1", expectItem())
                assertEquals("2", expectItem())
                assertEquals("3", expectItem())
                expectComplete()
            }
    }

}