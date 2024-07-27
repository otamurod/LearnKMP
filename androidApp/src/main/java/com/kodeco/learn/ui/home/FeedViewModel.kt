package com.kodeco.learn.ui.home

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.learn.ServiceLocator
import com.kodeco.learn.data.model.GravatarEntry
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.domain.cb.FeedData
import com.kodeco.learn.platform.Logger
import kotlinx.coroutines.launch

private const val TAG = "FeedViewModel"

class FeedViewModel : ViewModel(), FeedData {

  private val _items = mutableStateMapOf<PLATFORM, List<KodecoEntry>>()
  val items: SnapshotStateMap<PLATFORM, List<KodecoEntry>> = _items

  private val _profile = MutableLiveData(GravatarEntry())
  val profile: MutableLiveData<GravatarEntry> = _profile

  private val presenter by lazy {
    ServiceLocator.getFeedPresenter
  }

  fun fetchAllFeeds() {
    Logger.d(TAG, "fetchAllFeeds")
    viewModelScope.launch {
      presenter.fetchAllFeeds().collect {
        val platform = it.first().platform
        _items[platform] = it
      }
    }
  }

  fun fetchMyGravatar() {
    Logger.d(TAG, "fetchMyGravatar")
    presenter.fetchMyGravatar(this)
  }

  // region FeedData

  override fun onMyGravatarData(item: GravatarEntry) {
    Logger.d(TAG, "onMyGravatarData | item=$item")
    viewModelScope.launch {
      _profile.value = item
    }
  }

  // endregion FeedData
}
