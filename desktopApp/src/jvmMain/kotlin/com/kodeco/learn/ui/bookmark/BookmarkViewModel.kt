package com.kodeco.learn.ui.bookmark

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kodeco.learn.ServiceLocator
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.domain.cb.BookmarkData
import com.kodeco.learn.platform.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

private const val TAG = "BookmarkViewModel"

class BookmarkViewModel : ViewModel(), BookmarkData {

  val items: MutableState<List<KodecoEntry>> = mutableStateOf(emptyList())

  private val presenter by lazy {
    ServiceLocator.getBookmarkPresenter
  }

  fun getBookmarks() {
    Logger.d(TAG, "getBookmarks")
    presenter.getBookmarks(this)
  }

  fun addAsBookmark(entry: KodecoEntry) {
    Logger.d(TAG, "addAsBookmark")
    presenter.addAsBookmark(entry, this)
  }

  fun removeFromBookmark(entry: KodecoEntry) {
    Logger.d(TAG, "removeFromBookmark")
    presenter.removeFromBookmark(entry, this)
  }

  // region FeedData

  override fun onNewBookmarksList(bookmarks: List<KodecoEntry>) {
    Logger.d(TAG, "onNewBookmarksList | newItems=${bookmarks.size}")
    viewModelScope.launch {
      withContext(Dispatchers.Main) {
        items.value = bookmarks
      }
    }
  }

  // endregion FeedData
}
