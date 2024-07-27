package com.kodeco.learn.utils

import com.kodeco.learn.platform.Logger
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "Utils"

@Suppress("ConstantLocale")
private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

fun converterIso8601ToReadableDate(date: String): String {
  return try {
    val instant = date.toInstant()
    val millis = Date(instant.toEpochMilliseconds())
    return simpleDateFormat.format(millis)
  } catch (e: Exception) {
    Logger.w(TAG, "Error while converting dates. Error: $e")
    "-"
  }
}