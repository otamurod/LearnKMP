package com.kodeco.learn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.kodeco.learn.R
import com.kodeco.learn.platform.Logger

private const val TAG = "ImagePreview"

@Composable
fun AddImagePreview(
    url: String,
    modifier: Modifier
) {

  Logger.d(TAG, "Loading image from uri=$url")

  if (url.isEmpty()) {
    Logger.d(TAG, "Empty url")
    AddImagePreviewEmpty(modifier)

  } else {
    Box {

      val painter = rememberAsyncImagePainter(
        model = url
      )

      Image(
          painter = painter,
          contentScale = ContentScale.Crop,
          contentDescription = "Image preview",
          modifier = modifier
      )
    }
  }
}

@Composable
fun AddImagePreviewEmpty(
    modifier: Modifier
) {

  Column(
      modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Surface(
        modifier = modifier,
        color = Color.Transparent
    ) {

      val resource = painterResource(R.drawable.ic_brand)
      val description = stringResource(R.string.description_preview_error)

      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {

        Image(
            painter = resource,
            contentScale = ContentScale.Crop,
            contentDescription = description,
            modifier = modifier
        )
      }
    }
  }
}