package com.kodeco.learn.platform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import data.AppDb

public actual class Database {

  public actual fun createDatabase(): AppDb {
    return AppDb(createDriver())
  }

  private fun createDriver(): SqlDriver {
    return NativeSqliteDriver(AppDb.Schema, "app.db")
  }
}