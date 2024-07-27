package com.kodeco.learn.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.ui.common.AddEntryContent
import kotlinx.coroutines.CoroutineScope

private const val TAG = "SearchContent"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchContent(
    selected: MutableState<KodecoEntry>,
    items: SnapshotStateMap<PLATFORM, List<KodecoEntry>>,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    onOpenEntry: (String) -> Unit
) {

  Surface {

    val search = remember { mutableStateOf("") }

    val keys = items.keys

    val results = mutableListOf<KodecoEntry>()
    for (key in keys) {
      if (key == PLATFORM.ALL) {
        continue
      }

      Logger.d(TAG, "key=$key")

      val list = items[key]!!.toMutableList()

      Logger.d(TAG, "list=$list")
      results += list.filter {
        (it.title.lowercase().contains(search.value.lowercase())) ||
            (it.summary.lowercase().contains(search.value.lowercase()))
      }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),

        content = {

          item {
            AddSearchField(search)
          }

          items(results) {

            AddEntryContent(
                item = it,
                selected = selected,
                coroutineScope = coroutineScope,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                onOpenEntry = onOpenEntry
            )
          }
        }
    )
  }
}

@Composable
fun AddSearchField(search: MutableState<String>) {

  val focused = remember { mutableStateOf(false) }

  OutlinedTextField(
      value = search.value,
      onValueChange = { value ->
        search.value = value
      },
      modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .onFocusChanged {
            focused.value = it.isFocused
          },
      placeholder = {
        Text(
            text = "Search"
        )
      },
      leadingIcon = {
        val resource = painterResource("images/ic_search.xml")
        val description = "Search for a specific article"

        Icon(
            painter = resource,
            contentDescription = description
        )
      }
  )
}