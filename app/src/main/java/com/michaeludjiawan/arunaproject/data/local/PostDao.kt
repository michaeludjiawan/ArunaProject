package com.michaeludjiawan.arunaproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaeludjiawan.arunaproject.data.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM Post WHERE title LIKE '%' || :query || '%'")
    suspend fun getPosts(query: String): List<Post>
}