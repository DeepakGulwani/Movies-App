package com.example.inshortsassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inshortsassignment.ui.navigation.AppNavGraph
import com.example.inshortsassignment.ui.navigation.BottomNavBar
import com.example.inshortsassignment.ui.navigation.Screen
import com.example.inshortsassignment.ui.theme.InshortsAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single activity. Hilt entry point for the app.
 * Hosts the Compose NavHost and bottom navigation bar.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InshortsAssignmentTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    val topLevelRoutes = setOf(
        Screen.Home.route,
        Screen.Search.route,
        Screen.Bookmarks.route
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val currentRoute = navController
                .currentBackStackEntryAsState().value
                ?.destination?.route
            if (currentRoute in topLevelRoutes) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}