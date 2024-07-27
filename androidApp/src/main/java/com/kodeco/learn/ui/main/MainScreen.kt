package com.kodeco.learn.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kodeco.learn.data.model.GravatarEntry
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.ui.home.HomeSheetContent

private lateinit var selected: MutableState<KodecoEntry>

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    profile: GravatarEntry?,
    feeds: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
    bookmarks: State<List<KodecoEntry>?>,
    onUpdateBookmark: (KodecoEntry) -> Unit,
    onShareAsLink: (KodecoEntry) -> Unit,
    onOpenEntry: (String) -> Unit
) {

  val bottomNavigationItems = listOf(
      BottomNavigationScreens.Home,
      BottomNavigationScreens.Bookmark,
      BottomNavigationScreens.Latest,
      BottomNavigationScreens.Search
  )

  val navController = rememberNavController()
  navController.enableOnBackPressed(false)

  val coroutineScope = rememberCoroutineScope()
  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

  selected = remember { mutableStateOf(KodecoEntry()) }

  BottomSheetScaffold(
      sheetContent = {
        HomeSheetContent(
            coroutineScope = coroutineScope,
            bottomSheetScaffoldState = bottomSheetScaffoldState,
            item = selected,
            onUpdateBookmark = onUpdateBookmark,
            onShareAsLink = onShareAsLink
        )
      },
      sheetShape = RoundedCornerShape(
          topStart = 16.dp,
          topEnd = 16.dp
      ),
      scaffoldState = bottomSheetScaffoldState, sheetPeekHeight = 0.dp
  ) {

    Scaffold(
        topBar = {
          MainTopAppBar(
              profile = profile
          )
        },
        bottomBar = {
          MainBottomBar(
              navController = navController,
              items = bottomNavigationItems
          )
        },
        content = {
          Column(
              modifier = Modifier.padding(it)
          ) {
            MainContent(
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
    )
  }
}