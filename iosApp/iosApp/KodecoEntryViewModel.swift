import SwiftUI
import SharedKit

let TAG = "KodecoEntryViewModel"

class KodecoEntryViewModel: ObservableObject {
  @Published var items: [String: [KodecoEntry]] = [:]

  @Published var bookmarks: [KodecoEntry] = []

  @Published var profile: GravatarEntry?

  init() {
    fetchProfile()
    fetchFeeds()
  }

  func getContent() -> [KodecoContent] {
    FeedClient.shared.getContent()
  }

  func fetchProfile() {
    Task {
      guard let profile = await FeedClient.shared.fetchProfile() else { return }
      DispatchQueue.main.async {
        self.profile = profile
      }
    }
  }

  func fetchFeeds() {
    Task {
      let test = await FeedClient.shared.fetchFeeds()
      DispatchQueue.main.async {
        self.items = test
      }
    }
  }

  func fetchAllBookmarks() {
    BookmarkClient.shared.fetchBookmarks { items in
      Logger().d(tag: TAG, message: "fetchAllBookmarks: \(items.count) items")
      DispatchQueue.main.async {
        self.bookmarks = items
      }
    }
  }

  func addToBookmarks(entry: KodecoEntry) {
    BookmarkClient.shared.addToBookmarks(entry) { _ in
      Logger().d(tag: TAG, message: "addToBookmarks")
    }
  }

  func removeFromBookmarks(entry: KodecoEntry) {
    BookmarkClient.shared.removeFromBookmarks(entry) { _ in
      Logger().d(tag: TAG, message: "removeFromBookmarks")
    }
  }
}
