package com.kodeco.learn.data

import com.kodeco.learn.APP_NAME
import com.kodeco.learn.X_APP_NAME
import com.kodeco.learn.data.model.GravatarProfile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

public const val GRAVATAR_URL = "https://en.gravatar.com/"
public const val GRAVATAR_RESPONSE_FORMAT = ".json"

@ThreadLocal
public object FeedAPI {

  private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

  private val client: HttpClient = HttpClient {

    install(ContentNegotiation) {
      json(nonStrictJson)
    }

    install(Logging) {
      logger = HttpClientLogger
      level = LogLevel.HEADERS
    }
  }

  public suspend fun fetchKodecoEntry(feedUrl: String): HttpResponse = client.get(feedUrl)

  public suspend fun fetchImageUrlFromLink(link: String): HttpResponse = client.get(link) {
    header(HttpHeaders.Accept, ContentType.Text.Html)
  }

  public suspend fun fetchMyGravatar(hash: String): GravatarProfile =
      client.get("$GRAVATAR_URL$hash$GRAVATAR_RESPONSE_FORMAT"){
        header(X_APP_NAME, APP_NAME)
      }.body()
}