package com.kodeco.learn.ui.bookmark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.ui.common.AddEmptyScreen
import com.kodeco.learn.ui.common.AddEntryContent
import kotlinx.coroutines.CoroutineScope

private const val TAG = "BookmarkContent"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookmarkContent(
  selected: MutableState<KodecoEntry>,
  items: State<List<KodecoEntry>?>,
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  onOpenEntry: (String) -> Unit
) {

  Logger.d(TAG, "Items received | items=${items.value?.size}")

  if (items.value == null || items.value.isNullOrEmpty()) {
    AddEmptyScreen(
      text = "You currently don't have any bookmark."
    )
  } else {
    AddBookmarks(
      selected = selected,
      items = items.value ?: emptyList(),
      coroutineScope = coroutineScope,
      bottomSheetScaffoldState = bottomSheetScaffoldState,
      onOpenEntry = onOpenEntry
    )
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddBookmarks(
  selected: MutableState<KodecoEntry>,
  items: List<KodecoEntry>,
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  onOpenEntry: (String) -> Unit
) {

  val size = items.size

  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {

    itemsIndexed(items) { index, item ->
      AddEntryContent(
        item = item,
        selected = selected,
        divider = index < size - 1,
        coroutineScope = coroutineScope,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        onOpenEntry = onOpenEntry
      )
    }
  }
}