package com.kodeco.learn.ui.main

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

sealed class BottomNavigationScreens(
  val route: String,
  val title: String,
  val icon: @Composable () -> Unit
) {

  data object Home : BottomNavigationScreens(
    route = "Home",
    title = "Home",
    icon = {
      Icon(
        painter = painterResource("images/ic_home.xml"),
        contentDescription = "Home"
      )
    }
  )

  data object Bookmark : BottomNavigationScreens(
    route = "Bookmark",
    title = "Bookmark",
    icon = {
      Icon(
        painter = painterResource("images/ic_bookmarks.xml"),
        contentDescription = "Bookmark"
      )
    }
  )

  data object Latest : BottomNavigationScreens(
    route = "Latest",
    title = "Latest",
    icon = {
      Icon(
        painter = painterResource("images/ic_latest.xml"),
        contentDescription = "Latest"
      )
    }
  )

  data object Search : BottomNavigationScreens(
    route = "Search",
    title = "Search",
    icon = {
      Icon(
        painter = painterResource("images/ic_search.xml"),
        contentDescription = "Search"
      )
    }
  )
}