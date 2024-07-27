package com.kodeco.learn.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator

private lateinit var selectedIndex: MutableState<Int>

@Composable
fun MainBottomBar(
    navController: Navigator,
    items: List<BottomNavigationScreens>
) {

  Column {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {}

    AppBottomNavigation(
        navController = navController,
        items = items
    )
  }
}

@Composable
private fun AppBottomNavigation(
    navController: Navigator,
    items: List<BottomNavigationScreens>
) {
  BottomAppBar {

    selectedIndex = remember { mutableIntStateOf(0) }

    items.forEachIndexed { index, screen ->

      val isSelected = selectedIndex.value == index

      NavigationBarItem(
          icon = {
            screen.icon()
          },
          label = {
            Text(
                text = screen.title
            )
          },
          selected = isSelected,
          alwaysShowLabel = true,
          onClick = {
            if (!isSelected) {
              selectedIndex.value = index
              navController.navigate(screen.route)
            }
          }
      )
    }
  }
}