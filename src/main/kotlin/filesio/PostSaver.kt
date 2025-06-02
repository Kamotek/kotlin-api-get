package com.filesio

import com.model.Post
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


/**
 * object that uses PostSerialization singleton to store our posts locally in a specific folder
 */
object PostSaver {
    private val logger = LoggerFactory.getLogger("PostSaver")

    /**
     * @param destinationFolderPath it's a path where our posts will be stored
     * @param posts - list of posts, that will be serialized and stored in a specific folder
     */
    fun savePosts(destinationFolderPath: String, posts: List<Post>) {
        val destinationPath: Path = Paths.get(destinationFolderPath)

        try {
            Files.createDirectories(destinationPath)
            for (post in posts) {
                val json = PostSerializer.toJson(post)
                val filePath = destinationPath.resolve("${post.id}.json")
                Files.writeString(filePath, json)
            }
        } catch (e: Exception) {
            logger.error("Error saving file: ${e.message}", e)
        }
    }
}
