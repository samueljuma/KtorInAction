package com.samueljuma.ktorinaction.network

import com.samueljuma.ktorinaction.models.Comment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
    install(Logging){
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}

class CommentsAPIService {

    suspend fun getComments(): List<Comment> {
        return httpClient
            .get("https://jsonplaceholder.typicode.com/comments")
            .body<List<Comment>>()
    }
}

