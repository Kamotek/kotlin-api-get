package com.filesio

import com.model.Post
import kotlinx.serialization.json.Json

/**
 * I'm using this very simple singleton to serialize our Posts to JSON format
 * using prettyPrint allows me to store our posts more readable to end-user
 */
object PostSerializer {
    /*
     i'm using prettyPrint here, because it beautifully formats json file locally
     but i'm aware that this option should be turned off for network transmission because of
    */
    private val json = Json { prettyPrint = true }


    /**
     * @param post object, that we want to serialize
     * @return A JSON string representing serialized post
     */
    fun toJson(post: Post): String = json.encodeToString(post)
}
