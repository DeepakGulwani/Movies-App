# CineScope

A clean Android movies app built with Jetpack Compose. Browse trending and now-playing movies, search, and bookmark your favourites — all powered by the TMDB API.

## Features

- **Home** — Trending and Now Playing movies, loaded from TMDB with offline caching via Room
- **Search** — Debounced search (300ms) returning the first 20 results
- **Bookmarks** — Save movies locally and view them anytime, even offline
- **Detail Screen** — Poster, rating, release year, runtime, genres, and overview

## Tech Stack

- **UI** — Jetpack Compose + Material3
- **Architecture** — MVVM + Clean Architecture (Repository pattern)
- **DI** — Hilt
- **Networking** — Retrofit + OkHttp
- **Local DB** — Room
- **Images** — Coil
- **Async** — Kotlin Coroutines + Flow

## Setup

1. Get a free API key from [themoviedb.org](https://www.themoviedb.org/settings/api)
2. Add it to your `local.properties`:
   ```
   TMDB_API_KEY=your_api_key_here
   ```
3. Build and run

