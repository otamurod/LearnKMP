package com.kodeco.learn.platform

import platform.Foundation.NSLog

internal actual class Log {

  actual fun debug(tag: String, message: String) {
    NSLog("$tag | $message")
  }

  actual fun warn(tag: String, message: String) {
    NSLog("$tag | $message")
  }

  actual fun error(tag: String, message: String) {
    NSLog("$tag | $message")
  }
}