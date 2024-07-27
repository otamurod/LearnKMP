package com.kodeco.learn

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.ui.bookmark.BookmarkViewModel
import com.kodeco.learn.ui.home.FeedViewModel
import com.kodeco.learn.ui.main.MainScreen
import com.kodeco.learn.ui.theme.KodecoTheme
import moe.tlaster.precompose.PreComposeWindow
import moe.tlaster.precompose.viewmodel.viewModel
import java.awt.Desktop
import java.net.URI

private lateinit var bookmarkViewModel: BookmarkViewModel
private lateinit var feedViewModel: FeedViewModel

private const val TAG = "main"

fun main() {

  application {
    val windowState = rememberWindowState(width = 460.dp, height = 900.dp)

    PreComposeWindow(
      onCloseRequest = ::exitApplication,
      state = windowState,
      title = "Kodeco Learn"
    ) {
      bookmarkViewModel = viewModel {
        BookmarkViewModel()
      }

      feedViewModel = viewModel {
        FeedViewModel()
      }

      feedViewModel.fetchAllFeeds()
      feedViewModel.fetchMyGravatar()
      bookmarkViewModel.getBookmarks()

      val items = feedViewModel.items
      val profile = feedViewModel.profile
      val bookmarks = bookmarkViewModel.items

      Surface(modifier = Modifier.fillMaxSize()) {

        KodecoTheme {
          MainScreen(
            profile = profile.value,
            feeds = items,
            bookmarks = bookmarks,
            onUpdateBookmark = { updateBookmark(it) },
            onShareAsLink = { shareAsLink(it) },
            onOpenEntry = { openEntry(it) }
          )
        }
      }
    }
  }
}

private fun updateBookmark(item: KodecoEntry) {
  if (item.bookmarked) {
    removedFromBookmarks(item)
  } else {
    addToBookmarks(item)
  }
}

private fun addToBookmarks(item: KodecoEntry) {
  bookmarkViewModel.addAsBookmark(item)
  bookmarkViewModel.getBookmarks()
}

private fun removedFromBookmarks(item: KodecoEntry) {
  bookmarkViewModel.removeFromBookmark(item)
  bookmarkViewModel.getBookmarks()
}

private fun shareAsLink(item: KodecoEntry) {
  try {
    val desktop = Desktop.getDesktop()
    desktop.browse(URI.create(item.link))
  } catch(e: Exception) {
    Logger.d(TAG, "Unable to open url. Reason: ${e.stackTrace}")
  }
}

private fun openEntry(url: String) {
  try {
    val desktop = Desktop.getDesktop()
    desktop.browse(URI.create(url))
  } catch (e: Exception) {
    Logger.e(TAG, "Unable to open url. Reason: ${e.stackTrace}")
  }
}