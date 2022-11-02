package com.pram.bookcompose.presentation.feature.book.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pram.bookcompose.data.BookModel
import com.pram.bookcompose.presentation.common.observeAsState
import com.pram.bookcompose.ui.theme.Purple500
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun BookListScreen(
    bookListViewModel: BookListViewModel = viewModel(),
    navController: NavController
) {

    val books = bookListViewModel.books.value

    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()

    when (lifecycleState.value) {
        Lifecycle.Event.ON_RESUME -> {
            bookListViewModel.getBooks()
        }
        else -> Unit
    }

    BookListScreen(
        books = books,
        onBookClick = { clickedBook ->
            navController.navigate("bookDetail/${clickedBook.id}")
//            navController.navigate("bookDetail/")
        },
        onAddBookClick = {
        }
    )
}

@Composable
fun BookListScreen(
    books: List<BookModel>,
    onBookClick: (BookModel) -> Unit,
    onAddBookClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box {
            if (books.isNotEmpty()) {
                LazyColumn(
                ) {
                    items(
                        items = books,
                        key = { book -> book.id }
                    ) { bookModel ->
                        BookItemList(
                            book = bookModel,
                            onBookClick = { clickedBook ->
                                onBookClick.invoke(clickedBook)
                            }
                        )
                    }
                }
            }
            FloatingActionButton(
                backgroundColor = Purple500,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = {
                    onAddBookClick.invoke()
                }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    }
}

@Composable
fun BookItemList(
    book: BookModel,
    onBookClick: ((BookModel) -> Unit?)? = null
) {
    Card(
        backgroundColor = Teal200,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable(true, onClick = {
                onBookClick?.invoke(book)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Id: ${book.id}")
            Text(text = "Title: ${book.title}")
            Text(text = "Author: ${book.author}")
            Text(text = "Pages: ${book.pages}")
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "320"
)
@Preview(
    showBackground = true,
    widthDp = 480,
    name = "480"
)
@Composable
fun ScreenPreview() {
    val mockBooks = listOf(
        BookModel(
            "000",
            "title",
            "author",
            128
        ),
        BookModel(
            "001",
            "title",
            "author",
            256
        )
    )
    BookListScreen(
        books = mockBooks,
        onBookClick = {},
        onAddBookClick = {}
    )
}

@Preview
@Composable
fun BookItemListPreview() {
    BookItemList(
        book = BookModel(
            "000",
            "title",
            "author",
            128
        )
    )
}