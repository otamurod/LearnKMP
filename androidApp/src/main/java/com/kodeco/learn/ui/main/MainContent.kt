package com.kodeco.learn.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.ui.bookmark.BookmarkContent
import com.kodeco.learn.ui.home.HomeContent
import com.kodeco.learn.ui.latest.LatestContent
import com.kodeco.learn.ui.search.SearchContent
import kotlinx.coroutines.CoroutineScope

private val DEFAULT_SCREEN = BottomNavigationScreens.Home

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: NavHostController,
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
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    selected: MutableState<KodecoEntry>,
    feeds: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
    bookmarks: State<List<KodecoEntry>?>,
    onOpenEntry: (String) -> Unit
) {

  NavHost(
      navController,
      startDestination = DEFAULT_SCREEN.route
  ) {
    composable(BottomNavigationScreens.Home.route) {
      HomeContent(
          selected = selected,
          items = feeds,
          coroutineScope = coroutineScope,
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          onOpenEntry = onOpenEntry
      )
    }
    composable(BottomNavigationScreens.Bookmark.route) {
      BookmarkContent(
          selected = selected,
          items = bookmarks,
          coroutineScope = coroutineScope,
          bottomSheetScaffoldState = bottomSheetScaffoldState,
          onOpenEntry = onOpenEntry
      )
    }
    composable(BottomNavigationScreens.Latest.route) {
      LatestContent(
          items = feeds,
          onOpenEntry = onOpenEntry
      )
    }
    composable(BottomNavigationScreens.Search.route) {
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