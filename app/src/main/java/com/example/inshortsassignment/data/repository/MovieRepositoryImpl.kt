package com.example.inshortsassignment.data.repository

import com.example.inshortsassignment.data.local.dao.BookmarkDao
import com.example.inshortsassignment.data.local.dao.MovieDao
import com.example.inshortsassignment.data.mapper.toBookmarkEntity
import com.example.inshortsassignment.data.mapper.toDomain
import com.example.inshortsassignment.data.mapper.toEntity
import com.example.inshortsassignment.data.remote.api.TmdbApiService
import com.example.inshortsassignment.domain.model.Movie
import com.example.inshortsassignment.domain.model.MovieDetail
import com.example.inshortsassignment.domain.repository.MovieRepository
import com.example.inshortsassignment.util.Constants.MOVIE_TYPE_NOW_PLAYING
import com.example.inshortsassignment.util.Constants.MOVIE_TYPE_TRENDING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApiService,
    private val movieDao: MovieDao,
    private val bookmarkDao: BookmarkDao
) : MovieRepository {

    override fun getTrendingMovies(): Flow<List<Movie>> = flow {
        refreshMovies(MOVIE_TYPE_TRENDING)
        val bookmarkedIds = bookmarkDao.getAllBookmarks().first().map { it.id }.toSet()
        emitAll(
            movieDao.getMoviesByType(MOVIE_TYPE_TRENDING).map { entities ->
                entities.map { it.toDomain(isBookmarked = it.id in bookmarkedIds) }
            }
        )
    }

    override fun getNowPlayingMovies(): Flow<List<Movie>> = flow {
        refreshMovies(MOVIE_TYPE_NOW_PLAYING)
        val bookmarkedIds = bookmarkDao.getAllBookmarks().first().map { it.id }.toSet()
        emitAll(
            movieDao.getMoviesByType(MOVIE_TYPE_NOW_PLAYING).map { entities ->
                entities.map { it.toDomain(isBookmarked = it.id in bookmarkedIds) }
            }
        )
    }

    private suspend fun refreshMovies(type: String) {
        try {
            val response = when (type) {
                MOVIE_TYPE_TRENDING -> api.getTrendingMovies()
                else -> api.getNowPlayingMovies()
            }
            movieDao.deleteMoviesByType(type)
            movieDao.insertMovies(response.results.map { it.toEntity(type) })
        } catch (e: Exception) {

        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        val isBookmarked = bookmarkDao.isBookmarked(movieId).first()
        return api.getMovieDetail(movieId).toDomain(isBookmarked = isBookmarked)
    }

    override suspend fun searchMovies(query: String, page: Int): List<Movie> {
        if (query.isBlank()) return emptyList()
        return try {
            api.searchMovies(query, page).results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun toggleBookmark(movie: Movie) {
        val isCurrentlyBookmarked = bookmarkDao.isBookmarked(movie.id).first()
        if (isCurrentlyBookmarked) {
            bookmarkDao.deleteBookmark(movie.id)
        } else {
            bookmarkDao.insertBookmark(movie.toBookmarkEntity())
        }
    }

    override fun getBookmarkedMovies(): Flow<List<Movie>> =
        bookmarkDao.getAllBookmarks().map { entities ->
            entities.map { it.toDomain() }
        }

    override fun isBookmarked(movieId: Int): Flow<Boolean> =
        bookmarkDao.isBookmarked(movieId)
}
