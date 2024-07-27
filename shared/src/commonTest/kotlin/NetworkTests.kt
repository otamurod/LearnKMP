import com.kodeco.learn.data.GRAVATAR_RESPONSE_FORMAT
import com.kodeco.learn.data.GRAVATAR_URL
import com.kodeco.learn.data.model.GravatarEntry
import com.kodeco.learn.data.model.GravatarProfile
import com.kodeco.learn.platform.runTest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkTests {

  private val profile = GravatarProfile(
      entry = listOf(
          GravatarEntry(
              id = "1000",
              hash = "1000",
              preferredUsername = "Kodeco",
              thumbnailUrl = "https://avatars.githubusercontent.com/u/4722515?s=200&v=4"
          )
      )
  )

  private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

  private fun getHttpClient(): HttpClient {
    return HttpClient(MockEngine) {

      install(ContentNegotiation) {
        json(nonStrictJson)
      }

      engine {
        addHandler { request ->
          if (request.url.toString().contains(GRAVATAR_URL)) {
            respond(
                content = Json.encodeToString(profile),
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()))
          }
          else {
            error("Unhandled ${request.url}")
          }
        }
      }
    }
  }

  @Test
  public fun testFetchMyGravatar() = runTest {
    val client = getHttpClient()
    assertEquals(profile, client.request
    ("$GRAVATAR_URL${profile.entry[0].hash}$GRAVATAR_RESPONSE_FORMAT").body())
  }
}