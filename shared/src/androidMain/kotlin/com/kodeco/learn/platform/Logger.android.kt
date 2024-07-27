package com.kodeco.learn.platform

import android.util.Log

internal actual class Log {

  actual fun debug(tag: String, message: String) {
    Log.d(tag, message)
  }

  actual fun warn(tag: String, message: String) {
    Log.w(tag, message)
  }

  actual fun error(tag: String, message: String) {
    Log.e(tag, message)
  }
}