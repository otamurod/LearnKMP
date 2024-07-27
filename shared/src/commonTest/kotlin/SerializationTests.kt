import com.kodeco.learn.data.KodecoSerializer
import com.kodeco.learn.data.model.KodecoContent
import com.kodeco.learn.data.model.KodecoEntry
import com.kodeco.learn.data.model.PLATFORM
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.serializersModuleOf
import kotlinx.serialization.serializer
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationTests {

  private val serializers = serializersModuleOf(PLATFORM::class, KodecoSerializer)

  @Test
  fun testEncodePlatformAll() {
    val data = KodecoContent(
        platform = PLATFORM.ALL,
        url = "https://www.kodeco.com/feed.xml",
        image = "https://play-lh.googleusercontent.com/CAa4g9UbOJambautjl7lOfdiwjYoX04ORbivxdkPDZNirQd23TXQAfbFYPTN1VBWyzDt"
    )

    val decoded = Json.encodeToString(KodecoContent.serializer(), data)

    val content = "{\"platform\":\"all\",\"url\":\"https://www.kodeco.com/feed.xml\",\"image\":\"https://play-lh.googleusercontent.com/CAa4g9UbOJambautjl7lOfdiwjYoX04ORbivxdkPDZNirQd23TXQAfbFYPTN1VBWyzDt\"}"
    assertEquals(content, decoded)
  }

  @Test
  fun testDecodePlatformAll() {
    val data = "{\"platform\":\"all\",\"url\":\"https://www.kodeco.com/feed.xml\",\"image\":\"https://play-lh.googleusercontent.com/CAa4g9UbOJambautjl7lOfdiwjYoX04ORbivxdkPDZNirQd23TXQAfbFYPTN1VBWyzDt\"}"

    val decoded = Json.decodeFromString(KodecoContent.serializer(), data)
    val content = KodecoContent(
        platform = PLATFORM.ALL,
        url = "https://www.kodeco.com/feed.xml",
        image = "https://play-lh.googleusercontent.com/CAa4g9UbOJambautjl7lOfdiwjYoX04ORbivxdkPDZNirQd23TXQAfbFYPTN1VBWyzDt"
    )

    assertEquals(content, decoded)
  }

  @Test
  fun testEncodeCustomPlatformAll() {
    val data = PLATFORM.ALL

    val encoded = Json.encodeToString(serializers.serializer(), data)
    val expectedString = "\"${data.value.lowercase()}\""
    assertEquals(expectedString, encoded)
  }

  @Test
  fun testDecodeCustomPlatformAll() {
    val data = PLATFORM.ALL
    val jsonString = "\"${data.value}\""

    val decoded = Json.decodeFromString<PLATFORM>(jsonString)
    assertEquals(decoded, data)
  }

  @Test
  fun testEncodePlatformEntry() {
    val data = KodecoEntry(
        platform = PLATFORM.ALL,
        id = "https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine",
        link = "https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine",
        summary = "Learn to natively build your own SwiftUI camera app using Combine and create fun filters using the power of Core Image.",
        title = "Building a Camera App With SwiftUI and Combine [FREE]",
        updated = "2021-11-10T13:59:59Z"
    )

    val decoded = Json.encodeToString(KodecoEntry.serializer(), data)

    val content =
        "{\"id\":\"https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine\",\"link\":\"https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine\",\"title\":\"Building a Camera App With SwiftUI and Combine [FREE]\",\"summary\":\"Learn to natively build your own SwiftUI camera app using Combine and create fun filters using the power of Core Image.\",\"updated\":\"2021-11-10T13:59:59Z\"}"
    assertEquals(content, decoded)
  }

  @Test
  fun testDecodePlatformEntry() {
    val data =
        "{\"id\":\"https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine\",\"link\":\"https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine\",\"title\":\"Building a Camera App With SwiftUI and Combine [FREE]\",\"summary\":\"Learn to natively build your own SwiftUI camera app using Combine and create fun filters using the power of Core Image.\",\"updated\":\"2021-11-10T13:59:59Z\"}"

    val decoded = Json.decodeFromString(KodecoEntry.serializer(), data)
    val content = KodecoEntry(
        platform = PLATFORM.ALL,
        id = "https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine",
        link = "https://www.kodeco.com/26244793-building-a-camera-app-with-swiftui-and-combine",
        summary = "Learn to natively build your own SwiftUI camera app using Combine and create fun filters using the power of Core Image.",
        title = "Building a Camera App With SwiftUI and Combine [FREE]",
        updated = "2021-11-10T13:59:59Z"
    )

    assertEquals(content, decoded)
  }
}