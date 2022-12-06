package com.pram.bookcompose.presentation.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pram.bookcompose.presentation.feature.book.detail.BookDetailScreen
import com.pram.bookcompose.presentation.feature.book.list.BookListScreen
import com.pram.bookcompose.ui.theme.BookComposeTheme
import com.pram.bookcompose.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookComposeTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
                val navController = rememberNavController()
                var isShowBackButton by rememberSaveable { mutableStateOf(false) }
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    isShowBackButton = (destination.route != "bookList")
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    topBar = {
                        Box {
                            TopAppBar(
                                title = {
                                    Text(text = "TopAppBar")
                                },
                                navigationIcon = if (isShowBackButton) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Menu Btn",
                                            modifier = Modifier.clickable {
                                                navController.popBackStack()
                                            }
                                        )
                                    }
                                } else null,
                                backgroundColor = Purple500,
                                contentColor = Color.White,
                                elevation = 12.dp
                            )
                        }
                    }
                ) {
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
//                }
            }
        }
    }
}