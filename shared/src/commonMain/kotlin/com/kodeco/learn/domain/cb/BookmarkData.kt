package com.kodeco.learn.domain.cb

import com.kodeco.learn.data.model.KodecoEntry

public interface BookmarkData {

  public fun onNewBookmarksList(bookmarks: List<KodecoEntry>)
}