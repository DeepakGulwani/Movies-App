package com.example.inshortsassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with @HiltAndroidApp to enable Hilt
 * dependency injection across the entire app.
 */
@HiltAndroidApp
class MoviesApp : Application()
