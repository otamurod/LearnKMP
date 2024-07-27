package com.kodeco.learn.presentation

import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.domain.cb.BookmarkData
import com.kodeco.learn.domain.dao.KodecoEntryDAO
import com.kodeco.learn.platform.Logger

private const val TAG = "BookmarkPresenter"

class BookmarkPresenter(private val kodecoEntryDAO: KodecoEntryDAO) {

  private var listener: BookmarkData? = null

  public fun getBookmarks(cb: BookmarkData) {
    Logger.d(TAG, "getBookmarks")
    listener = cb
    getBookmarks()
  }

  private fun getBookmarks() {}

  public fun addAsBookmark(entry: KodecoEntry, cb: BookmarkData) {
    Logger.d(TAG, "addAsBookmark")
    listener = cb
    addAsBookmark(entry.copy(bookmarked = true))
  }

  private fun addAsBookmark(entry: KodecoEntry) {}

  public fun removeFromBookmark(entry: KodecoEntry, cb: BookmarkData) {
    Logger.d(TAG, "removeFromBookmark")
    listener = cb
    removeFromBookmark(entry.copy(bookmarked = false))
  }

  private fun removeFromBookmark(entry: KodecoEntry) {}
}