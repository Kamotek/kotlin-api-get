package com.model

import kotlinx.serialization.Serializable

/**
 * That's our data class that works as a representation of posts
 */
@Serializable
data class Post(val userId: Int, val id: Int, val title: String, val body: String)