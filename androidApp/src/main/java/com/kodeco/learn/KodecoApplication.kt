package com.kodeco.learn

import android.app.Application
import com.kodeco.learn.platform.appContext

class KodecoApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    appContext = applicationContext
  }
}