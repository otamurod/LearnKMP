package com.kodeco.learn.data

import com.kodeco.learn.platform.Logger

private const val TAG = "HttpClientLogger"

public object HttpClientLogger : io.ktor.client.plugins.logging.Logger {

  override fun log(message: String) {
    Logger.d(TAG, message)
  }
}