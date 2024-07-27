import SwiftUI
import SDWebImageSwiftUI

struct MainToolbarContent: ToolbarContent {
  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var body: some ToolbarContent {
    ToolbarItem(placement: .primaryAction) {
      Button(action: {
        // Do nothing
      }, label: {
        if let avatarUrl = feedViewModel.profile?.thumbnailUrl {
          AnimatedImage(url: URL(string: avatarUrl))
            .resizable()
            .frame(width: 30, height: 30)
            .scaledToFill()
            .clipShape(Circle())
        } else {
          Image("ic_person")
            .resizable()
            .frame(width: 30, height: 30)
        }
      })
    }
  }
}
