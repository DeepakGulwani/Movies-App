package com.example.inshortsassignment.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val runtime: Int?,
    val genres: List<Genre>,
    val tagline: String,
    val status: String,
    val isBookmarked: Boolean = false
)

data class Genre(
    val id: Int,
    val name: String
)
