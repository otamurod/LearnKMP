package com.kodeco.learn.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kodeco.learn.ServiceLocator
import com.kodeco.learn.components.AddImagePreview
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.ui.common.AddEntryContent
import kotlinx.coroutines.CoroutineScope

private const val TAG = "HomeContent"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
  selected: MutableState<KodecoEntry>,
  items: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  onOpenEntry: (String) -> Unit
) {

  val size = items.size
  val platform = remember { mutableStateOf(PLATFORM.ALL) }

  Logger.d(TAG, "Items received | items=${items.size}")

  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {

    item {
      Spacer(modifier = Modifier.height(16.dp))
    }

    item {
      AddPlatformHeadings(
        platform = platform
      )
    }

    item {
      Spacer(modifier = Modifier.height(16.dp))
    }

    if (!items.isEmpty()) {
      val feed = items[platform.value] ?: emptyList()
      itemsIndexed(feed) { index, item ->
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
}

@Composable
fun AddPlatformHeadings(
  platform: MutableState<PLATFORM>
) {

  LazyRow(
    modifier = Modifier.fillMaxWidth()
  ) {

    items(ServiceLocator.getFeedPresenter.content) {

      Spacer(modifier = Modifier.width(16.dp))

      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        AddImagePreview(
          url = it.image,
          modifier = Modifier
            .size(125.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
              platform.value = it.platform
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = it.platform.value
        )
      }
    }

    item {
      Spacer(modifier = Modifier.width(16.dp))
    }
  }
}