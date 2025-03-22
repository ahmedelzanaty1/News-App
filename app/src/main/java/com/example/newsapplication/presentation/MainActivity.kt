package com.example.newsapplication.presentation

import CategoryScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.newsapplication.conestant.Route
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapplication.domain.model.ArticleModel
import com.example.newsapplication.presentation.screens.DetailsScreen.DetailsScreen
import com.example.newsapplication.presentation.screens.HomeScreen.HomeScreen
import com.example.newsapplication.presentation.screens.SettingScreen.SettingScreen
import com.example.newsapplication.presentation.theme.NewsApplicationTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale.Category

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            NewsApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun NavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    androidx.navigation.compose.NavHost(navController , startDestination = Route.CATEGORY)  {
        composable(Route.CATEGORY) {
            CategoryScreen(navController = navController)
        }
        composable(Route.SETTING) {
            SettingScreen(navController = navController)
        }
        composable("${Route.HOME}/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "News"
            HomeScreen(navController = navController, categoryName = category)
        }
        composable(
            route = "details/{articleJson}",
            arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("articleJson")

            if (!articleJson.isNullOrEmpty()) {
                val article = Gson().fromJson(articleJson, ArticleModel::class.java)
                DetailsScreen(navController = navController, article = article)
            }
        }


    }
}