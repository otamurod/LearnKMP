package com.kodeco.learn.data.model

import com.kodeco.learn.data.KodecoSerializer
import com.kodeco.learn.platform.Parcelable
import com.kodeco.learn.platform.Parcelize
import kotlinx.serialization.Serializable

@Serializable(with = KodecoSerializer::class)
enum class PLATFORM(val value: String) {

  ALL("All"),
  ANDROID("Android"),
  IOS("iOS"),
  FLUTTER("Flutter"),
  SERVER("Server"),
  GAME_TECH("GameTech"),
  GROWTH("Growth")
}

@Parcelize
@Serializable
data class KodecoContent(
    val platform: PLATFORM,
    val url: String,
    val image: String
) : Parcelable