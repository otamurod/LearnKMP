import SwiftUI
import SharedKit
import SDWebImageSwiftUI

struct HomeView: View {
  let TAG = "HomeView"

  @State private var filter = PLATFORM.all.description()

  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var body: some View {
    let content = feedViewModel.getContent()

    let rows = [GridItem(.fixed(150))]

    NavigationView {
      ZStack(alignment: .topLeading) {
        Color("black-night")
        ScrollView(.vertical) {
          VStack {
            ScrollView(.horizontal) {
              LazyHGrid(rows: rows, alignment: .top) {
                ForEach(content, id: \.self) { item in
                  Button(action: {
                    self.filter = item.platform.description()
                  }, label: {
                    AnimatedImage(url: URL(string: "\(item.image)"))
                      .resizable()
                      .scaledToFit()
                      .cornerRadius(8)
                  })
                }
              }.scenePadding()

              Spacer()
            }

            let items = feedViewModel.items[filter] ?? []

            ForEach(items, id: \.id) { item in
              KodecoEntryRow(item: item, addToBookmarks: true)
                .environmentObject(feedViewModel)
            }
          }
        }
        .navigationTitle("Kodeco Learn")
        .toolbar {
          MainToolbarContent()
        }
      }
    }
  }
}
