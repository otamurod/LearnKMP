package com.kodeco.learn.data.model

import com.kodeco.learn.platform.Parcelable
import com.kodeco.learn.platform.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class KodecoEntry(
    val id: String = "",
    val link: String = "",
    val title: String = "",
    val summary: String = "",
    val updated: String = "",
    val platform: PLATFORM = PLATFORM.ALL,
    var imageUrl: String = "",
    val bookmarked: Boolean = false
) : Parcelable