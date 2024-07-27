import SwiftUI
import SharedKit
import SDWebImageSwiftUI

struct BookmarkView: View {
  let TAG = "BookmarkView"

  @State private var showDialog = false

  @State private var selectedEntry: KodecoEntry?

  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var body: some View {
    NavigationView {
      ZStack(alignment: .topLeading) {
        Color("black-night")
        if feedViewModel.bookmarks.isEmpty {
          VStack(alignment: .center) {
            Text("You currently don't have any bookmark.")
              .foregroundColor(.white)
              .font(Font.custom("OpenSans-Bold", size: 15))
          }
          .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
          .background(Color("black-night"))
        } else {
          ScrollView(.vertical) {
            ForEach(feedViewModel.bookmarks, id: \.id) { item in
              KodecoEntryRow(item: item, addToBookmarks: false)
                .environmentObject(feedViewModel)
            }
          }
        }
      }
      .navigationTitle("learn")
      .toolbar {
        MainToolbarContent()
      }
    }
    .onAppear {
      Logger().d(tag: TAG, message: "Retrieving all bookmarks")
      feedViewModel.fetchAllBookmarks()
    }
  }
}
