import SwiftUI
import SharedKit
import SDWebImageSwiftUI

struct LatestView: View {
  let TAG = "LatestView"

  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var body: some View {
    NavigationView {
      ZStack {
        Color("black-night")
        ScrollView(.vertical) {
          VStack {
            ForEach(Array(feedViewModel.items.keys.sorted()), id: \.self) { key in
              let item = feedViewModel.items[key] ?? []
              Section(platform: item.first?.platform.value ?? key, entries: item)
            }
          }
          .navigationBarTitle("learn", displayMode: .inline)
          .toolbar {
            MainToolbarContent()
          }
        }
      }
    }
  }
}

let itemsSection = 4

struct Section: View {
  @Environment(\.openURL)
  var openURL

  @State var platform: String

  var entries: [KodecoEntry]

  var body: some View {
    let max = entries.count < itemsSection ? entries.count : itemsSection
    let subEntries = max == 0 ? [] : entries[0...max]

    if !subEntries.isEmpty {
      LazyVStack {
        HStack {
          Text(platform)
            .foregroundColor(.white)
            .font(Font.custom("OpenSans-Bold", size: 18))
            .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)

          Spacer()
        }
        .padding(.leading, 10)
        .frame(maxWidth: .infinity)

        TabView {
          ForEach(subEntries, id: \.id) { item in
            VStack {
              Button(action: {
                guard let url = URL(string: "\(item.link)") else {
                  return
                }

                openURL(url)
              }, label: {
                if item.imageUrl.isEmpty {
                  Rectangle().foregroundColor(.gray)
                  Image("kodeco")
                } else {
                  AnimatedImage(url: URL(string: "\(item.imageUrl)"))
                    .resizable()
                    .scaledToFill()
                    .cornerRadius(8)
                }
              })
            }
          }
        }

        .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.width)
        .tabViewStyle(PageTabViewStyle())
      }
    }
  }
}
