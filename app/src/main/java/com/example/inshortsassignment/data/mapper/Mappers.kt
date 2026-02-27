package com.example.inshortsassignment.data.mapper

import com.example.inshortsassignment.data.local.entity.BookmarkEntity
import com.example.inshortsassignment.data.local.entity.MovieEntity
import com.example.inshortsassignment.data.remote.dto.GenreDto
import com.example.inshortsassignment.data.remote.dto.MovieDetailDto
import com.example.inshortsassignment.data.remote.dto.MovieDto
import com.example.inshortsassignment.domain.model.Genre
import com.example.inshortsassignment.domain.model.Movie
import com.example.inshortsassignment.domain.model.MovieDetail

fun MovieDto.toEntity(type: String) = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genreIds = genreIds.joinToString(","),
    popularity = popularity,
    type = type
)

fun MovieEntity.toDomain(isBookmarked: Boolean = false) = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genreIds = if (genreIds.isBlank()) emptyList()
               else genreIds.split(",").mapNotNull { it.trim().toIntOrNull() },
    popularity = popularity,
    isBookmarked = isBookmarked
)

fun BookmarkEntity.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genreIds = if (genreIds.isBlank()) emptyList()
               else genreIds.split(",").mapNotNull { it.trim().toIntOrNull() },
    popularity = popularity,
    isBookmarked = true
)

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genreIds = genreIds,
    popularity = popularity
)

fun GenreDto.toDomain() = Genre(id = id, name = name)

fun MovieDetailDto.toDomain(isBookmarked: Boolean = false) = MovieDetail(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    runtime = runtime,
    genres = genres.map { it.toDomain() },
    tagline = tagline,
    status = status,
    isBookmarked = isBookmarked
)

fun Movie.toBookmarkEntity() = BookmarkEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    genreIds = genreIds.joinToString(","),
    popularity = popularity
)
