package com.kodeco.learn.domain

import com.kodeco.learn.data.FeedAPI
import com.kodeco.learn.data.model.GravatarEntry
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import com.kodeco.learn.platform.Logger
import io.ktor.client.statement.bodyAsText
import korlibs.io.serialization.xml.Xml
import korlibs.io.util.substringAfterOrNull
import korlibs.io.util.substringBeforeOrNull

private const val TAG = "GetFeedData"

private const val WEBSITE_PREVIEW_ARTICLE_START_DELIMITER = "<div class=\"triad-top-right\">\n        <img src=\""

private const val WEBSITE_PREVIEW_ARTICLE_END_DELIMITER = "\" />"

private const val WEBSITE_PREVIEW_BOOK_START_DELIMITER =
  "<img alt=\"\" class=\"c-tutorial-item__art-image--primary\" loading=\"lazy\" src=\""

private const val WEBSITE_PREVIEW_BOOK_END_DELIMITER = "\" />"

public class GetFeedData {

  public suspend fun invokeFetchKodecoEntry(
      platform: PLATFORM,
      imageUrl: String,
      feedUrl: String
  ): List<KodecoEntry> {
    return try {
      val result = FeedAPI.fetchKodecoEntry(feedUrl)

      Logger.d(TAG, "invokeFetchKodecoEntry | feedUrl=$feedUrl")

      val xml = Xml.parse(result.bodyAsText())

      val feed = mutableListOf<KodecoEntry>()
      for (node in xml.allNodeChildren) {
        val parsed = parseNode(platform, imageUrl, node)

        if (parsed != null) {
          feed += parsed
        }
      }

      feed
    } catch (e: Exception) {
      Logger.e(TAG, "Unable to fetch feed:$feedUrl. Error: $e")
      emptyList()
    }
  }

  public suspend fun invokeFetchImageUrlFromLink(
      link: String
  ): String {
    return try {

      val result = FeedAPI.fetchImageUrlFromLink(link)
      parsePage(link, result.bodyAsText())

    } catch (e: Exception) {
      ""
    }
  }

  public suspend fun invokeGetMyGravatar(
      hash: String,
  ): GravatarEntry {
    return try {
      val result = FeedAPI.fetchMyGravatar(hash)
      Logger.d(TAG, "invokeGetMyGravatar | result=$result")

      if (result.entry.isEmpty()) {
        GravatarEntry()
      } else {
        result.entry[0]
      }
    } catch (e: Exception) {
      Logger.e(TAG, "Unable to fetch my gravatar. Error: $e")
      GravatarEntry()
    }
  }
}

private fun parsePage(url: String, content: String): String {
  val start = if (url.contains("books", true)) {
    content.substringAfterOrNull(WEBSITE_PREVIEW_BOOK_START_DELIMITER)
  } else {
    content.substringAfterOrNull(WEBSITE_PREVIEW_ARTICLE_START_DELIMITER)
  }
  val end = if (url.contains("books", true)) {
    start?.substringBeforeOrNull(WEBSITE_PREVIEW_BOOK_END_DELIMITER)
 } else {
    start?.substringBeforeOrNull(WEBSITE_PREVIEW_ARTICLE_END_DELIMITER)
  }
  return end ?: ""
}

private fun parseNode(platform: PLATFORM, imageUrl: String, entry: Xml): KodecoEntry? {
  if (entry.name == "entry") {
    val id = entry.allNodeChildren.firstOrNull { it.name == "id" }
    val link = entry.allNodeChildren.firstOrNull { it.name == "link" }
    val title = entry.allNodeChildren.firstOrNull { it.name == "title" }
    val summary = entry.allNodeChildren.firstOrNull { it.name == "summary" }
    val updated = entry.allNodeChildren.firstOrNull { it.name == "updated" }

    return KodecoEntry(
      id = id?.text ?: "",
      link = link?.attributesLC?.get("href") ?: "",
      title = title?.text ?: "",
      summary = summary?.text ?: "",
      updated = updated?.text ?: "",
      platform = platform,
      imageUrl = imageUrl
    )
  } else {
    return null
  }
}