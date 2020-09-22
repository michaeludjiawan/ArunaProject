package com.michaeludjiawan.arunaproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaeludjiawan.arunaproject.data.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao
}