package com.kodeco.learn.data

import com.kodeco.learn.data.model.PLATFORM
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object KodecoSerializer : KSerializer<PLATFORM> {

  override val descriptor: SerialDescriptor =
      PrimitiveSerialDescriptor("PLATFORM", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: PLATFORM) {
    encoder.encodeString(value.value.lowercase())
  }

  override fun deserialize(decoder: Decoder): PLATFORM {
    return try {
      val key = decoder.decodeString()
      findByKey(key)
    } catch (e: IllegalArgumentException) {
      PLATFORM.ALL
    }
  }
}

private fun findByKey(key: String, default: PLATFORM = PLATFORM.ALL): PLATFORM {
  return PLATFORM.values().find { it.value.lowercase() == key.lowercase() } ?: default
}