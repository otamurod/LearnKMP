package com.kodeco.learn.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.kodeco.learn.R
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.ui.bookmark.BookmarkViewModel
import com.kodeco.learn.ui.home.FeedViewModel
import com.kodeco.learn.ui.main.MainScreen
import com.kodeco.learn.ui.theme.KodecoTheme

class MainActivity : ComponentActivity() {

  private val bookmarkViewModel: BookmarkViewModel by viewModels()
  private val feedViewModel: FeedViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    feedViewModel.fetchAllFeeds()
    feedViewModel.fetchMyGravatar()
    bookmarkViewModel.getBookmarks()

    setContent {

      val items = feedViewModel.items
      val profile = feedViewModel.profile.observeAsState()
      val bookmarks = bookmarkViewModel.items.observeAsState()

      KodecoTheme {
        MainScreen(
          profile = profile.value,
          feeds = items,
          bookmarks = bookmarks,
          onUpdateBookmark = { onUpdateBookmark(it) },
          onShareAsLink = { shareAsLink(it) },
          onOpenEntry = { openEntry(it) }
        )
      }
    }
  }

  private fun onUpdateBookmark(item: KodecoEntry) {
    if (item.bookmarked) {
      removedFromBookmarks(item)
    } else {
      addToBookmarks(item)
    }
  }

  private fun addToBookmarks(item: KodecoEntry) {
    Toast.makeText(applicationContext, R.string.action_added_bookmarks, Toast.LENGTH_SHORT).show()
    bookmarkViewModel.addAsBookmark(item)
    bookmarkViewModel.getBookmarks()
  }

  private fun removedFromBookmarks(item: KodecoEntry) {
    Toast.makeText(applicationContext, R.string.action_removed_bookmarks, Toast.LENGTH_SHORT).show()
    bookmarkViewModel.removeFromBookmark(item)
    bookmarkViewModel.getBookmarks()
  }

  private fun shareAsLink(item: KodecoEntry) {
    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, getString(R.string.action_share_link_text, item.link))
      type = "text/plain"
    }

    startActivity(Intent.createChooser(sendIntent, null))
  }

  private fun openEntry(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
  }
}