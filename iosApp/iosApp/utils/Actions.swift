import SwiftUI
import SharedKit

func shareLink(entry: KodecoEntry) {
  let allScenes = UIApplication.shared.connectedScenes
  let scene = allScenes.first { $0.activationState == .foregroundActive }

  if let windowScene = scene as? UIWindowScene {
    guard let data = URL(string: entry.link) else { return }
    let avController = UIActivityViewController(activityItems: [data], applicationActivities: nil)
    windowScene.keyWindow?.rootViewController?.present(avController, animated: true, completion: nil)
  }
}
