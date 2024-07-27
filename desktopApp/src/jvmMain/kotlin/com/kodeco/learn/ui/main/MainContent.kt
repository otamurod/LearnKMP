package com.kodeco.learn.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.ui.bookmark.BookmarkContent
import com.kodeco.learn.ui.home.HomeContent
import com.kodeco.learn.ui.latest.LatestContent
import com.kodeco.learn.ui.search.SearchContent
import kotlinx.coroutines.CoroutineScope
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

private val DEFAULT_SCREEN = BottomNavigationScreens.Home

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: Navigator,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selected: MutableState<KodecoEntry>,
    feeds: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
    bookmarks: State<List<KodecoEntry>?>,
    onOpenEntry: (String) -> Unit
) {

  Column {
    MainScreenNavigationConfigurations(
        navController = navController,
        coroutineScope = coroutineScope,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        selected = selected,
        feeds = feeds,
        bookmarks = bookmarks,
        onOpenEntry = onOpenEntry
    )
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreenNavigationConfigurations(
    navController: Navigator,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selected: MutableState<KodecoEntry>,
    feeds: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
    bookmarks: State<List<KodecoEntry>?>,
    onOpenEntry: (String) -> Unit
) {

  NavHost(
      navigator = navController,
      initialRoute = DEFAULT_SCREEN.route
  ) {
    scene(BottomNavigationScreens.Home.route) {
      HomeContent(
          selected = selected,
          items = feeds,
          coroutineScope = coroutineScope,
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          onOpenEntry = onOpenEntry
      )
    }
    scene(BottomNavigationScreens.Bookmark.route) {
      BookmarkContent(
          selected = selected,
          items = bookmarks,
          coroutineScope = coroutineScope,
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          onOpenEntry = onOpenEntry
      )
    }
    scene(BottomNavigationScreens.Latest.route) {
      LatestContent(
          items = feeds,
          onOpenEntry = onOpenEntry
      )
    }
    scene(BottomNavigationScreens.Search.route) {
      SearchContent(
          selected = selected,
          items = feeds,
          coroutineScope = coroutineScope,
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          onOpenEntry = onOpenEntry
      )
    }
  }
}