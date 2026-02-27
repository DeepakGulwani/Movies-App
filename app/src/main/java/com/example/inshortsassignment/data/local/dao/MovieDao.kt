package com.example.inshortsassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inshortsassignment.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for the movies cache table.
 * All queries return Flow so Room automatically re-emits
 * whenever the underlying data changes.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE type = :type")
    suspend fun deleteMoviesByType(type: String)
}
