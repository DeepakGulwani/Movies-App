package com.example.inshortsassignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity that caches both trending and now-playing movies.
 * The [type] column distinguishes between the two lists so they
 * can share a single table.
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val genreIds: String,   // stored as comma-separated string
    val popularity: Double,
    val type: String        // "trending" or "now_playing"
)
