package com.example.inshortsassignment.domain.repository

import com.example.inshortsassignment.domain.model.Movie
import com.example.inshortsassignment.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTrendingMovies(): Flow<List<Movie>>

    fun getNowPlayingMovies(): Flow<List<Movie>>

    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun searchMovies(query: String, page: Int = 1): List<Movie>

    suspend fun toggleBookmark(movie: Movie)

    fun getBookmarkedMovies(): Flow<List<Movie>>

    fun isBookmarked(movieId: Int): Flow<Boolean>
}
