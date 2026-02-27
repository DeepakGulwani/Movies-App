package com.example.inshortsassignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for user-bookmarked movies. Persisted independently
 * from the cache so bookmarks survive cache clears.
 */
@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val genreIds: String,
    val popularity: Double
)
