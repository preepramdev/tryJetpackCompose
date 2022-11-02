package com.pram.bookcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pram.bookcompose.presentation.feature.book.detail.BookDetailScreen
import com.pram.bookcompose.presentation.feature.book.list.BookListScreen
import com.pram.bookcompose.ui.theme.BookComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "bookList") {
                        composable(route = "bookList") {
                            BookListScreen(navController = navController)
                        }
                        composable(
                            route = "bookDetail/{bookId}",
                            arguments = listOf(
                                navArgument("bookId") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            BookDetailScreen(
                                bookId = backStackEntry.arguments?.getString("bookId"),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}