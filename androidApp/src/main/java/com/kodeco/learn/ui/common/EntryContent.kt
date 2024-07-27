package com.kodeco.learn.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kodeco.learn.R
import com.kodeco.learn.components.AddImagePreview
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.platform.Logger
import com.kodeco.learn.utils.converterIso8601ToReadableDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "EntryContent"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEntryContent(
  item: KodecoEntry,
  selected: MutableState<KodecoEntry>,
  divider: Boolean = true,
  coroutineScope: CoroutineScope,
  bottomSheetScaffoldState: BottomSheetScaffoldState,
  onOpenEntry: (String) -> Unit
) {

  Logger.d(TAG, "item | title=${item.title} | updated=${item.updated}")


  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
      .clickable {
        onOpenEntry(item.link)
      }
  ) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1.0f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ) {

        AddImagePreview(
          url = item.imageUrl,
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
          modifier = Modifier.fillMaxWidth()
        ) {

          Text(
            text = stringResource(R.string.app_kodeco)
          )

          Text(
            text = converterIso8601ToReadableDate(item.updated)
          )
        }
      }

      Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
      ) {

        val resource = painterResource(R.drawable.ic_more)
        val description = stringResource(R.string.description_more)

        Icon(
          painter = resource,
          contentDescription = description,
          modifier = Modifier
            .size(25.dp)
            .clickable {
              selected.value = item
              coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.expand()
              }
            }
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = item.title
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = item.summary,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )

    Spacer(modifier = Modifier.height(16.dp))

    if (divider) {
      Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp
      )
    }
  }
}