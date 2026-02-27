package com.example.inshortsassignment.util

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w1280"

    const val DATABASE_NAME = "movies_db"
    const val MOVIE_TYPE_TRENDING = "trending"
    const val MOVIE_TYPE_NOW_PLAYING = "now_playing"

    const val SEARCH_DEBOUNCE_MS = 300L
    const val NETWORK_TIMEOUT_SECONDS = 30L
    const val DEFAULT_PAGE = 1
}
