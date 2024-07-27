package com.kodeco.learn.domain.cb

import com.kodeco.learn.data.model.GravatarEntry
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM

public interface FeedData {

  public fun onMyGravatarData(item: GravatarEntry)
}