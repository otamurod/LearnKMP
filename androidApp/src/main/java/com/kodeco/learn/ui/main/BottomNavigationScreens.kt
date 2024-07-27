package com.kodeco.learn.ui.main

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kodeco.learn.R

sealed class BottomNavigationScreens(
  val route: String,
  @StringRes val title: Int,
  val icon: @Composable () -> Unit
) {

  data object Home : BottomNavigationScreens(
    route = "Home",
    title = R.string.navigation_home,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_home),
        contentDescription = stringResource(R.string.navigation_home)
      )
    }
  )

  data object Bookmark : BottomNavigationScreens(
    route = "Bookmark",
    title = R.string.navigation_bookmark,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_bookmarks),
        contentDescription = stringResource(R.string.navigation_bookmark)
      )
    }
  )

  data object Latest : BottomNavigationScreens(
    route = "Latest",
    title = R.string.navigation_latest,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_latest),
        contentDescription = stringResource(R.string.navigation_latest)
      )
    }
  )

  data object Search : BottomNavigationScreens(
    route = "Search",
    title = R.string.navigation_search,
    icon = {
      Icon(
        painter = painterResource(R.drawable.ic_search),
        contentDescription = stringResource(R.string.navigation_search)
      )
    }
  )
}