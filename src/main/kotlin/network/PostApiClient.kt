package com.network

import com.model.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory


/**
 * PostApiClient
 * This object is used to connect with our API endpoint and fetch our posts
 */

/*
 i decided to use object keyword, because thanks to that i can use it as a singleton, without
 worrying about creating some instances or something
*/
object PostApiClient {
    private val logger = LoggerFactory.getLogger("PostApiClient")

    /*
     thanks to initializing HttpClient here, our singleton does not have to
     initialize it every time we call savePosts
     */
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
        }
    }

    /**
     * @param postApiUrl - it's our endpoint, that's stored in config file
     * @return posts - list of deserialized posts
     * if there are some problems with connection, it will simply return empty list
     */
    suspend fun getPosts(postApiUrl: String): List<Post> {
        return try {
            val response: HttpResponse = httpClient.get(postApiUrl)

            if (response.status != HttpStatusCode.OK) {
                logger.error("Failed to fetch posts. HTTP status: ${response.status}")
                return emptyList()
            }

            try {
                response.body()
            } catch (e: SerializationException) {
                logger.error("Failed to deserialize response body into List<Post>: ${e.message}", e)
                emptyList()
            }
        }
        catch (e: Exception) {
            logger.error("Unexpected error when fetching posts: ${e.message}", e)
            emptyList()
        }
    }

    fun close() {
        httpClient.close()
    }
}