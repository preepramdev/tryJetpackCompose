package com.pram.bookcompose.presentation.feature.book.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.pram.bookcompose.data.BookModel
import com.pram.bookcompose.presentation.common.observeAsState
import com.pram.bookcompose.presentation.feature.book.detail.uistate.BookDetailErrorUiState
import com.pram.bookcompose.presentation.widget.button.NormalButton
import com.pram.bookcompose.presentation.widget.button.OutlineButton
import com.pram.bookcompose.presentation.widget.dialog.OneButtonDialog
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun BookDetailScreen(
    bookDetailViewModel: BookDetailViewModel = viewModel(),
    bookId: String?,
    navController: NavController
) {

    val book = bookDetailViewModel.book.value
    val bookDetailErrorUiState = bookDetailViewModel.showError.value

    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()

    when (lifecycleState.value) {
        Lifecycle.Event.ON_RESUME -> {
            bookDetailViewModel.getBook(bookId)
        }
        else -> Unit
    }

    BookDetailScreen(
        book,
        bookDetailErrorUiState
    ) { bookDetailViewModel.hideDialog() }
}

@Composable
fun BookDetailScreen(
    book: BookModel?,
    bookDetailErrorUiState: BookDetailErrorUiState?,
    onErrorDialog: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box {
            Column {
                Card(
                    backgroundColor = Teal200,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        BookDetailRow(header = "Id", detail = book?.id.orEmpty())
                        BookDetailRow(header = "Title", detail = book?.title.orEmpty())
                        BookDetailRow(header = "Author", detail = book?.author.orEmpty())
                        BookDetailRow(header = "Pages", detail = "${book?.pages ?: ""}")
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalButton(
                        text = "Update",
                        modifier = Modifier.padding(top = 16.dp)
                    ) {

                    }
                    OutlineButton(
                        text = "Remove",
                        modifier = Modifier.padding(top = 8.dp)
                    ) {

                    }
                }
            }
            if (bookDetailErrorUiState is BookDetailErrorUiState.SomethingWrongError) {
                OneButtonDialog(
                    onButtonClick = onErrorDialog
                )
            }
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
fun BookDetailScreenPreview() {
    val mockBookModel = BookModel(
        id = "0",
        title = "title 0",
        author = "author 0",
        pages = 10
    )
    BookDetailScreen(mockBookModel, null) {}
}