package com.example.inshortsassignment.ui.bookmarks

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

data class BookmarksUiState(
    val bookmarks: List<Movie> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarksUiState())
    val uiState: StateFlow<BookmarksUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getBookmarkedMovies()
                .catch { _uiState.value = _uiState.value.copy(isLoading = false) }
                .collect { bookmarks ->
                    _uiState.value = BookmarksUiState(bookmarks = bookmarks, isLoading = false)
                }
        }
    }
}
