package com.kodeco.learn.ui.latest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.kodeco.learn.components.AddImagePreview
import com.kodeco.learn.components.HorizontalPagerIndicator
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.ui.common.AddEmptyScreen
import com.kodeco.learn.ui.theme.colorContent20Transparency
import com.kodeco.learn.ui.theme.colorContent85Transparency

private const val TAG = "LatestContent"

private const val ITEMS = 5

@Composable
fun LatestContent(
  items: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
  onOpenEntry: (String) -> Unit
) {

  if (items.keys.isEmpty()) {
    AddEmptyScreen("Loading…")
  } else {
    AddPages(
      items = items,
      onOpenEntry = onOpenEntry
    )
  }
}

@Composable
fun AddPages(
  items: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
  onOpenEntry: (String) -> Unit
) {

  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {

    item {
      Spacer(modifier = Modifier.height(16.dp))
    }

    items(items.keys.toMutableList().sortedBy { it.name }) { platform ->

      Logger.d(TAG, "Adding platform=$platform")

      AddNewPage(
        platform = platform,
        items = items[platform]?.subList(0, ITEMS) ?: emptyList(),
        onOpenEntry = onOpenEntry
      )
    }

    item {
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddNewPage(
  platform: PLATFORM,
  items: List<KodecoEntry>,
  onOpenEntry: (String) -> Unit
) {
  val pagerState = rememberPagerState(pageCount = {
    items.size
  })

  Column(
    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
  ) {
    Text(
      text = platform.value
    )

    HorizontalPager(
      state = pagerState
    ) { page ->

      AddNewPageEntry(
        entry = items[page],
        onOpenEntry = onOpenEntry
      )
    }

    if (items.size > 1) {
      HorizontalPagerIndicator(
        pagerState = pagerState,
        pageCount = items.size,
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(16.dp),
        activeColor = MaterialTheme.colorScheme.secondaryContainer
      )
    }
  }
}

@Composable
fun AddNewPageEntry(
  entry: KodecoEntry,
  onOpenEntry: (String) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .aspectRatio(1.0f)
      .padding(top = 8.dp, bottom = 8.dp)
      .clickable {
        onOpenEntry(entry.link)
      },
    shape = RoundedCornerShape(16.dp)
  ) {

    AddImagePreview(
      url = entry.imageUrl,
      modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(16.dp))
    )

    val verticalGradientBrush = Brush.verticalGradient(
      colors = listOf(
        colorContent20Transparency,
        colorContent85Transparency
      )
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(brush = verticalGradientBrush),
      verticalArrangement = Arrangement.Bottom,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Text(
        text = entry.title,
        modifier = Modifier.padding(16.dp)
      )
    }
  }
}