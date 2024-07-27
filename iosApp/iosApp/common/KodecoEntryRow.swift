import SwiftUI
import SharedKit
import SDWebImageSwiftUI

struct KodecoEntryRow: View {
  let item: KodecoEntry

  let addToBookmarks: Bool

  @Environment(\.openURL)
  var openURL

  @State private var showDialog = false

  @State private var selectedEntry: KodecoEntry?

  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var body: some View {
    Button(action: {
      guard let url = URL(string: "\(item.link)") else {
        return
      }
      openURL(url)
    }, label: {
      VStack(alignment: .leading) {
        HStack {
          if item.imageUrl.isEmpty {
            Rectangle().foregroundColor(.gray)
            Image("kodeco")
              .resizable()
              .frame(width: 50, height: 50)
          } else {
            AnimatedImage(url: URL(string: "\(item.imageUrl)"))
              .resizable()
              .frame(width: 50, height: 50)
              .cornerRadius(8)
          }

          VStack(alignment: .leading) {
            Text("Kodeco")
              .foregroundColor(.white)
              .font(Font.custom("OpenSans-Bold", size: 15))
            Text(formatStringDate(date: item.updated))
              .foregroundColor(.white)
              .font(Font.custom("OpenSans-Bold", size: 12))
          }

          Spacer()

          Button(action: {
            selectedEntry = item
            showDialog = true
          }, label: {
            Image("ic_more").foregroundColor(.white)
          })
            .padding(.trailing, 1)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        Text(item.title)
          .lineLimit(2)
          .foregroundColor(.white)
          .font(Font.custom("OpenSans-Bold", size: 15))
          .multilineTextAlignment(.leading)
        Text(item.summary)
          .lineLimit(2)
          .foregroundColor(.white)
          .font(Font.custom("OpenSans-Regular", size: 14))
          .multilineTextAlignment(.leading)
      }
      .padding(.horizontal, 16.0)
    })
      .confirmationDialog(
        "",
        isPresented: $showDialog,
        presenting: $selectedEntry
      ) { entry in
        if addToBookmarks {
          Button("Add to bookmarks") {
            if let entry = entry.wrappedValue {
              feedViewModel.addToBookmarks(entry: entry)
            }
          }
        } else {
          Button("Remove from bookmarks") {
            if let entry = entry.wrappedValue {
              feedViewModel.removeFromBookmarks(entry: entry)
            }
          }
        }
        Button("Share as link") {
          if let entry = entry.wrappedValue {
            shareLink(entry: entry)
          }
        }
        Button("Cancel", role: .cancel) {
          selectedEntry = nil
        }
      }
  }
}
