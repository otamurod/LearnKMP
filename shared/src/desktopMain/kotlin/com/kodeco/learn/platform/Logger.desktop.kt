package com.kodeco.learn.platform

internal actual class Log {

  actual fun debug(tag: String, message: String) {
    println("$tag | $message")
  }

  actual fun warn(tag: String, message: String) {
    println("$tag | $message")
  }

  actual fun error(tag: String, message: String) {
    println("$tag | $message")
  }
}