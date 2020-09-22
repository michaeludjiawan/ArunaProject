package com.michaeludjiawan.arunaproject.data.repository

import com.michaeludjiawan.arunaproject.data.api.Result
import com.michaeludjiawan.arunaproject.data.model.Post

interface PostRepository {
    suspend fun getPosts(query: String): Result<List<Post>>
}