package com.example.inshortsassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inshortsassignment.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmarks ORDER BY title ASC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :movieId")
    suspend fun deleteBookmark(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :movieId)")
    fun isBookmarked(movieId: Int): Flow<Boolean>
}
