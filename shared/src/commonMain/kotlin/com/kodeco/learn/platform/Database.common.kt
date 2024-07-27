package com.kodeco.learn.platform

import data.AppDb

public expect class Database() {

  public fun createDatabase(): AppDb
}