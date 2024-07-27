import SharedKit
import KMPNativeCoroutinesAsync

public class FeedClient {
  private init() { }

  public typealias FeedHandlerImage = (_ url: String) -> Void

  public static let shared = FeedClient()

  private let feedPresenter = ServiceLocator.init().getFeedPresenter

  private var handlerImage: FeedHandlerImage?

  private var items: [String: [KodecoEntry]] = [:]

  public func getContent() -> [KodecoContent] {
    return feedPresenter.content
  }

  public func fetchProfile() async -> GravatarEntry? {
    let result = await asyncResult(for: feedPresenter.fetchMyGravatar())
    switch result {
    case .success(let value):
      return value
    case .failure(let value):
      Logger().e(tag: TAG, message: "Unable to fetch profile. Reason:\(value)")
      return nil
    }
  }

  public func fetchFeeds() async -> [String: [KodecoEntry]] {
    do {
      let result = asyncSequence(for: feedPresenter.fetchAllFeeds())
      for try await data in result {
        guard let item = data.first else { continue }
        self.items[item.platform.name] = data
      }
    } catch {
      Logger().e(tag: TAG, message: "Unable to fetch all feeds")
    }
    return self.items
  }
}

extension FeedClient: FeedData {
  public func onMyGravatarData(item: GravatarEntry) {
    Logger().d(tag: TAG, message: "onMyGravatarData")
  }
}
