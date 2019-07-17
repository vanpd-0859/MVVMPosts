package com.sun.mvvmposts.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.mvvmposts.model.Post
import com.sun.mvvmposts.model.PostDao

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}