package com.config

/**
 * Config object that can store various values
 */
object Config {
    val POST_API = System.getenv("POST_API_URL")
        ?: "https://jsonplaceholder.typicode.com/posts"
}