import SwiftUI
import SharedKit

struct SearchView: View {
  @Binding var text: String

  @State private var isEditing = false

  @State private var showDialog = false

  @State private var selectedEntry: KodecoEntry?

  @EnvironmentObject private var feedViewModel: KodecoEntryViewModel

  var filteredItems: [KodecoEntry] {
    if self.text.isEmpty {
      return feedViewModel.items[PLATFORM.all.description()] ?? []
    } else {
      let all = feedViewModel.items[PLATFORM.all.description()] ?? []
      return all.filter { $0.title.contains(text) || $0.summary.contains(text) }
    }
  }

  var body: some View {
    NavigationView {
      ZStack(alignment: .topLeading) {
        Color("black-night")
        ScrollView(.vertical) {
          VStack {
            HStack {
              TextField("Search...", text: $text)
                .padding(8)
                .padding(.horizontal, 25)
                .background(Color(.systemGray6))
                .cornerRadius(8)
                .overlay(
                  HStack {
                    Image(systemName: "magnifyingglass")
                      .foregroundColor(.gray)
                      .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                      .padding(.leading, 8)

                    if isEditing {
                      Button(action: {
                        self.text = ""
                      }, label: {
                        Image(systemName: "multiply.circle.fill")
                          .foregroundColor(.gray)
                          .padding(.trailing, 8)
                      })
                    }
                  }
                )
                .padding(.horizontal, 10)
                .onTapGesture {
                  self.isEditing = true
                }

              if isEditing {
                Button(action: {
                  self.isEditing = false
                  self.text = ""

                  UIApplication.shared.sendAction(
                    #selector(UIResponder.resignFirstResponder),
                    to: nil,
                    from: nil,
                    for: nil)
                }, label: {
                  Text("Cancel")
                })
                .padding(.trailing, 10)
                .transition(.move(edge: .trailing))
              }
            }
            .padding(.vertical, 8.0)

            ForEach(filteredItems, id: \.id) { item in
              KodecoEntryRow(item: item, addToBookmarks: true)
                .environmentObject(feedViewModel)
            }
          }
        }
      }
      .navigationBarTitle("Kodeco Learn", displayMode: .inline)
      .toolbar {
        MainToolbarContent()
      }
    }
  }
}
