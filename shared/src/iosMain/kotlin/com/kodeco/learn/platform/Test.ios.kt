package com.kodeco.learn.platform

import kotlinx.coroutines.runBlocking

public actual fun runTest(block: suspend () -> Unit) = runBlocking { block() }