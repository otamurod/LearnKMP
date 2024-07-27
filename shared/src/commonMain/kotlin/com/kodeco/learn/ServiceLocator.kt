package com.kodeco.learn

import com.kodeco.learn.domain.GetFeedData
import com.kodeco.learn.domain.dao.KodecoEntryDAO
import com.kodeco.learn.platform.Database
import com.kodeco.learn.presentation.BookmarkPresenter
import com.kodeco.learn.presentation.FeedPresenter

public object ServiceLocator {

  private val KodecoEntryDao by lazy {
    val db = Database().createDatabase()
    KodecoEntryDAO(db)
  }

  private val getFeed: GetFeedData = GetFeedData()

  public val getFeedPresenter: FeedPresenter = FeedPresenter(getFeed)

  public val getBookmarkPresenter: BookmarkPresenter = BookmarkPresenter(KodecoEntryDao)
}