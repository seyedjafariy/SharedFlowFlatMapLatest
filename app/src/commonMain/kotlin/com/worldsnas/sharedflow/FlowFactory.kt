package com.worldsnas.sharedflow

import kotlinx.coroutines.flow.*

object FlowFactory {
    val sharedFlow = MutableSharedFlow<String>()

    val stateFlow = MutableStateFlow("1")

    fun composeSharedFlow(call: (String) -> Unit) {
        sharedFlow
            .flatMapLatest {
                flowOf(it)
            }
            .wrap()
            .watch(call)
    }

    fun composeNormalFlow(call : (String) -> Unit){
        flowOf("1", "2")
            .flatMapLatest {
                flowOf(it)
            }
            .wrap()
            .watch(call)
    }

    fun composeStateFlow(call : (String) -> Unit) {
        stateFlow
            .flatMapLatest {
                flowOf(it)
            }
            .wrap()
            .watch(call)
    }


}