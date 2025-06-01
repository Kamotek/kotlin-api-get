package com

import com.config.Config
import com.io.PostSaver
import com.model.Post
import com.network.PostApiClient
import org.slf4j.LoggerFactory


private val logger = LoggerFactory.getLogger("Main")

suspend fun main() {

    print("Please name a folder that will store our posts: ")
    val destinationFolder = readln()

    try{
    val posts: List<Post> = PostApiClient.getPosts(Config.POST_API)
    PostSaver.savePosts(destinationFolder, posts)
    } catch(e: Exception){
        logger.error("Error saving posts: HTTP ${e.message}")
    }
}