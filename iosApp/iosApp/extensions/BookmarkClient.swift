import SharedKit

public class BookmarkClient {
  public typealias BookmarkHandler = (_ items: [KodecoEntry]) -> Void

  public static let shared = BookmarkClient()

  private let bookmarkPresenter = ServiceLocator.init().getBookmarkPresenter
  private var handler: BookmarkHandler?

  public func fetchBookmarks(completion: @escaping BookmarkHandler) {
    BookmarkClient.shared.bookmarkPresenter.getBookmarks(cb: BookmarkClient.shared)
    BookmarkClient.shared.handler = completion
  }

  public func addToBookmarks(_ entry: KodecoEntry, completion: @escaping BookmarkHandler) {
    Logger().d(tag: TAG, message: "addToBookmarks | entry=\(entry)")
    BookmarkClient.shared.bookmarkPresenter.addAsBookmark(entry: entry, cb: BookmarkClient.shared)
  }

  public func removeFromBookmarks(_ entry: KodecoEntry, completion: @escaping BookmarkHandler) {
    Logger().d(tag: TAG, message: "removeFromBookmarks")
    BookmarkClient.shared.bookmarkPresenter.removeFromBookmark(entry: entry, cb: BookmarkClient.shared)
  }
}

extension BookmarkClient: BookmarkData {
  public func onNewBookmarksList(bookmarks: [KodecoEntry]) {
    Logger().d(tag: TAG, message: "onNewBookmarksList: \(bookmarks)")
    self.handler?(bookmarks)
  }
}
