package com.io

import com.model.Post
import kotlinx.serialization.json.Json

object PostSerializer {
    private val json = Json { prettyPrint = true }

    fun toJson(post: Post): String = json.encodeToString(post)
}
