package com.kodeco.learn.platform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver.Companion.IN_MEMORY
import data.AppDb

public actual class Database {

  public actual fun createDatabase(): AppDb {
    return AppDb(createDriver())
  }

  private fun createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver(IN_MEMORY)
    AppDb.Schema.create(driver)
    return driver
  }
}