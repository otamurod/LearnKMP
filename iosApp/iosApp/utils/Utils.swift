import SwiftUI
import SharedKit
import Foundation

func formatStringDate(date: String) -> String {
  let dateFormatter = DateFormatter()
  dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
  let newDate = dateFormatter.date(from: date)
  dateFormatter.setLocalizedDateFormatFromTemplate("MMMM d, yyyy")
    if let newDate = newDate {
      return dateFormatter.string(from: newDate)
    } else {
      return ""
    }
}
