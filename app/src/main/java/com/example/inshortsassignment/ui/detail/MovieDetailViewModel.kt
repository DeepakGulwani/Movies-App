package com.example.inshortsassignment.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inshortsassignment.domain.model.Movie
import com.example.inshortsassignment.domain.model.MovieDetail
import com.example.inshortsassignment.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailUiState(
    val movieDetail: MovieDetail? = null,
    val isBookmarked: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState(isLoading = true)
            try {
                val detail = repository.getMovieDetail(movieId)
                _uiState.value = _uiState.value.copy(movieDetail = detail, isLoading = false)

                repository.isBookmarked(movieId)
                    .onEach { bookmarked ->
                        _uiState.value = _uiState.value.copy(isBookmarked = bookmarked)
                    }
                    .launchIn(viewModelScope)

            } catch (e: Exception) {
                _uiState.value = MovieDetailUiState(
                    isLoading = false,
                    error = "Failed to load movie details."
                )
            }
        }
    }

    fun toggleBookmark(detail: MovieDetail) {
        viewModelScope.launch {
            val movie = Movie(
                id = detail.id,
                title = detail.title,
                overview = detail.overview,
                posterPath = detail.posterPath,
                backdropPath = detail.backdropPath,
                voteAverage = detail.voteAverage,
                releaseDate = detail.releaseDate,
                genreIds = detail.genres.map { it.id },
                popularity = 0.0,
                isBookmarked = _uiState.value.isBookmarked
            )
            repository.toggleBookmark(movie)
        }
    }
}
