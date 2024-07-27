import SwiftUI
import SharedKit

struct ContentView: View {
  @State private var tabSelection = 1

  @State private var searchText = ""

  @StateObject var feedViewModel = KodecoEntryViewModel()

  init() {
    let itemAppearance = UITabBarItemAppearance()
    itemAppearance.normal.iconColor = UIColor(Color.white)

    let tabBarAppearance = UITabBarAppearance()
    tabBarAppearance.backgroundColor = UIColor(Color("black-night"))
    tabBarAppearance.stackedLayoutAppearance = itemAppearance
    tabBarAppearance.inlineLayoutAppearance = itemAppearance
    tabBarAppearance.compactInlineLayoutAppearance = itemAppearance

    UITabBar.appearance().standardAppearance = tabBarAppearance
    if #available(iOS 15.0, *) {
      UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
    }

    let navBarAppearance = UINavigationBarAppearance()
    navBarAppearance.backgroundColor = UIColor(Color("black-night"))
    navBarAppearance.shadowImage = UIImage()
    navBarAppearance.shadowColor = .clear
    navBarAppearance.backgroundImage = UIImage()
    navBarAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor.white]

    if let uiFont = UIFont(name: "OpenSans-Bold", size: 18) {
    navBarAppearance.titleTextAttributes = [
    .font: uiFont,
    .foregroundColor: UIColor.white
    ]}

    UINavigationBar.appearance().barTintColor = UIColor(Color("black-night"))
    UINavigationBar.appearance().isTranslucent = false
    UINavigationBar.appearance().standardAppearance = navBarAppearance
    UINavigationBar.appearance().scrollEdgeAppearance = navBarAppearance
    UINavigationBar.appearance().compactAppearance = navBarAppearance
  }

  var body: some View {
    TabView(selection: $tabSelection) {
      HomeView()
        .tabItem {
          Image("ic_home")
          Text("Home")
        }
        .tag(1)
        .environmentObject(feedViewModel)

      BookmarkView()
        .tabItem {
          Image("ic_bookmark")
          Text("Bookmark")
        }
        .tag(2)
        .environmentObject(feedViewModel)

      LatestView()
        .tabItem {
          Image("ic_latest")
          Text("Latest")
        }
        .tag(3)
        .environmentObject(feedViewModel)

      SearchView(text: $searchText)
        .tabItem {
          Image("ic_search")
          Text("Search")
        }
        .tag(4)
        .environmentObject(feedViewModel)
    }
    .accentColor(Color("orange-glow"))
  }
}
