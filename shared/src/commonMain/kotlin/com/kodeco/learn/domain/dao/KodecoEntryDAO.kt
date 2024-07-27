package com.kodeco.learn.domain.dao

import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import data.AppDb

public class KodecoEntryDAO(database: AppDb) {

  private val db = database.kodecoEntryModelQueries

  public fun insertOrReplace(entries: List<KodecoEntry>) {
    for (entry in entries) {
      insertOrReplace(entry)
    }
  }

  public fun insertOrReplace(entry: KodecoEntry) {
    db.insertOrReplaceKodecoEntry(
      id = entry.id,
      link = entry.link,
      title = entry.title,
      summary = entry.summary,
      updated = entry.updated,
      platform = PLATFORM.values().indexOf(entry.platform).toLong(),
      imageUrl = entry.imageUrl,
      bookmarked = if (entry.bookmarked) 1 else 0
    )
  }

  public fun remove(entry: KodecoEntry) {
    db.removeKodecoEntry(
      id = entry.id
    )
  }

  public fun getAllEntries(): List<KodecoEntry> {
    val data = db.selectAllKodecoEntries().executeAsList()

    val entries = mutableListOf<KodecoEntry>()
    for (item in data) {
      entries += KodecoEntry(
        id = item.id,
        link = item.link,
        title = item.title,
        summary = item.summary,
        updated = item.updated,
        platform = PLATFORM.values()[item.platform.toInt()],
        imageUrl = item.imageUrl,
        bookmarked = item.bookmarked == 1L
      )
    }

    return entries
  }

  public fun clearAllEntries() {
    db.clearAllKodecoEntries()
  }
}