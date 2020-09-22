package com.michaeludjiawan.arunaproject.data.api

import com.michaeludjiawan.arunaproject.data.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}