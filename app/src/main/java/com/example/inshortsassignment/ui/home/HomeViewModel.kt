package com.example.inshortsassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inshortsassignment.domain.model.Movie
import com.example.inshortsassignment.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val trendingMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val isTrendingLoading: Boolean = true,
    val isNowPlayingLoading: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadTrending()
        loadNowPlaying()
    }

    private fun loadTrending() {
        viewModelScope.launch {
            repository.getTrendingMovies()
                .catch { _uiState.value = _uiState.value.copy(isTrendingLoading = false) }
                .collect { movies ->
                    _uiState.value = _uiState.value.copy(
                        trendingMovies = movies,
                        isTrendingLoading = false
                    )
                }
        }
    }

    private fun loadNowPlaying() {
        viewModelScope.launch {
            repository.getNowPlayingMovies()
                .catch { _uiState.value = _uiState.value.copy(isNowPlayingLoading = false) }
                .collect { movies ->
                    _uiState.value = _uiState.value.copy(
                        nowPlayingMovies = movies,
                        isNowPlayingLoading = false
                    )
                }
        }
    }
}
