package com.service

import com.model.Post
import com.network.PostApiClient

/**
 * PostService – class that works as a business layer for our post api fetcher
 *
 * @param apiClient – httpClient that downloads all the data from provided endpoint
 */
class PostService(
    private val apiClient: PostApiClient
) {
    /**
     * we're downloading all the posts from the api
     */
    suspend fun getAllPosts(apiUrl: String): List<Post> {
        return apiClient.getPosts(apiUrl)
    }
}
