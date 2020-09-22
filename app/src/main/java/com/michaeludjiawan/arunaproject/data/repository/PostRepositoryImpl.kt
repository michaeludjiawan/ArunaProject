package com.michaeludjiawan.arunaproject.data.repository

import com.michaeludjiawan.arunaproject.data.api.ApiService
import com.michaeludjiawan.arunaproject.data.api.Result
import com.michaeludjiawan.arunaproject.data.local.AppDb
import com.michaeludjiawan.arunaproject.data.local.AppPreferences
import com.michaeludjiawan.arunaproject.data.model.Post
import com.michaeludjiawan.arunaproject.util.Constants.API_CACHE_DURATION
import com.michaeludjiawan.arunaproject.util.Constants.API_LAST_FETCH_TIMESTAMP

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val appDb: AppDb,
    private val appPref: AppPreferences
) : PostRepository {

    override suspend fun getPosts(query: String): Result<List<Post>> {
        if (!isCacheValid()) {
            val result = getPostsFromServer()
            if (result is Result.Error) return result
        }

        val posts = getPostsFromLocal(query)
        return Result.Success(posts)
    }

    // Cache is invalid if duration exceed API_CACHE_DURATION or doesn't exist
    private fun isCacheValid(): Boolean {
        if (!appPref.contains(API_LAST_FETCH_TIMESTAMP)) return false

        val lastFetch = appPref.getLong(API_LAST_FETCH_TIMESTAMP)
        val elapsedTime = System.currentTimeMillis() - lastFetch

        return elapsedTime < API_CACHE_DURATION
    }

    private suspend fun getPostsFromLocal(query: String): List<Post> {
        return appDb.postDao().getPosts(query)
    }

    private suspend fun getPostsFromServer(): Result<List<Post>> {
        return try {
            val posts = apiService.getPosts()
            savePostsToLocal(posts)
            appPref.save(API_LAST_FETCH_TIMESTAMP, System.currentTimeMillis())
            Result.Success(posts)
        } catch (throwable: Throwable) {
            Result.Error(throwable)
        }
    }

    private suspend fun savePostsToLocal(posts: List<Post>) {
        appDb.postDao().insertAll(posts)
    }
}