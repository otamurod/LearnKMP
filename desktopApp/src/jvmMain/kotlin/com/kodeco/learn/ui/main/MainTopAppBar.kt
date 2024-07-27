package com.kodeco.learn.ui.main

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kodeco.learn.components.AddImagePreview
import com.kodeco.learn.data.model.GravatarEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    profile: GravatarEntry?
) {

  CenterAlignedTopAppBar(
      title = {
        Text(
            text = "learn"
        )
      },
      actions = {
        IconButton(onClick = {
          //Do nothing
        }) {
          val avatarUrl = profile?.thumbnailUrl
          if (avatarUrl == null) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "User profile"
            )
          } else {
            AddImagePreview(
                url = avatarUrl,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp)
            )
          }
        }
      }
  )
}