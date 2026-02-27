package com.example.inshortsassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inshortsassignment.data.local.dao.BookmarkDao
import com.example.inshortsassignment.data.local.dao.MovieDao
import com.example.inshortsassignment.data.local.entity.BookmarkEntity
import com.example.inshortsassignment.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun bookmarkDao(): BookmarkDao
}
