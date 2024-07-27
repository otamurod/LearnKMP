package com.kodeco.learn.platform

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import data.AppDb

public lateinit var appContext: Context

public actual class Database {

  public actual fun createDatabase(): AppDb {
    return AppDb(createDriver())
  }

  private fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(AppDb.Schema, appContext, "app.db")
  }
}