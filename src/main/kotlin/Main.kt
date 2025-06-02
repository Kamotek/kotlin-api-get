package com

import com.config.Config
import com.filesio.PostSaver
import com.network.PostApiClient
import com.service.PostService
import org.slf4j.LoggerFactory
import kotlinx.coroutines.runBlocking

private val logger = LoggerFactory.getLogger("Main")


/**
 * main function, catches user's input
 * and calls such functions as fetching and saving data
 * at the end it closes http connection
 */
fun main() = runBlocking {

    /*
    i thought about extracting this part to separate function
    but i decided to leave it here, because it's very simple piece of code
    and atomizing it would result in unnecesary level of code complexity
     */
    print("Please name a folder that will store our posts: ")
    val destinationFolder = readlnOrNull()?.trim().takeIf { !it.isNullOrBlank() }
        ?: run {
            logger.error("No input detected")
            return@runBlocking
        }

    val service = PostService(PostApiClient)

    try {
        val posts = service.getAllPosts(Config.POST_API)

        PostSaver.savePosts(destinationFolder, posts)
    } catch (e: Exception) {
        logger.error("Error saving posts: ${e.message}", e)
    } finally {
        PostApiClient.close()
    }
}
