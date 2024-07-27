package com.kodeco.learn.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.platform.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "HomeSheetContent"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeSheetContent(
  item: MutableState<KodecoEntry>,
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  onUpdateBookmark: (KodecoEntry) -> Unit,
  onShareAsLink: (KodecoEntry) -> Unit
) {

  Logger.d(TAG, "Selected item=${item.value}")

  Column(
    modifier = Modifier.fillMaxWidth()
  ) {

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable {
          coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
          }

          onUpdateBookmark(item.value)
        }
    ) {

      val text = if (item.value.bookmarked) {
        "Remove from bookmarks"
      } else {
        "Add to bookmarks"
      }

      Text(
        text = text,
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 12.dp)
      )
    }

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable {
          coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
          }

          onShareAsLink(item.value)
        }
    ) {
      Text(
        text = "Share as link",
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 24.dp)
      )
    }
  }
}