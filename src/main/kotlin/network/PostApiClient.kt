package com.network

import com.model.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.slf4j.LoggerFactory

object PostApiClient {
    private val logger = LoggerFactory.getLogger("PostApiClient")

    suspend fun getPosts(postApiUrl: String): List<Post> {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json()

            }
        }

        val response: HttpResponse = client.get(postApiUrl)
        client.close()

        if(response.status != HttpStatusCode.OK){
            logger.error("Failed to fetch posts: HTTP ${response.status}")
            return emptyList()
        }

        val posts: List<Post> = response.body()

        return posts
    }
}