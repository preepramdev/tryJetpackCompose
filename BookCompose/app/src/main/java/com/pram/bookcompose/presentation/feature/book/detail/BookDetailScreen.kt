package com.pram.bookcompose.presentation.feature.book.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.pram.bookcompose.data.BookModel
import com.pram.bookcompose.presentation.common.observeAsState
import com.pram.bookcompose.presentation.widget.dialog.OneButtonDialog
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun BookDetailScreen(
    bookDetailViewModel: BookDetailViewModel = viewModel(),
    bookId: String?,
    navController: NavController
) {

    val book = bookDetailViewModel.book.value
    val showErrorSomethingWrong = bookDetailViewModel.showErrorSomethingWrong.value

    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()

    when (lifecycleState.value) {
        Lifecycle.Event.ON_RESUME -> {
            bookDetailViewModel.getBook(bookId)
        }
        else -> Unit
    }

    BookDetailScreen(book, showErrorSomethingWrong, {navController.popBackStack()})
}

@Composable
fun BookDetailScreen(
    book: BookModel?,
    showErrorSomethingWrong: Boolean,
    onErrorDialog: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box {
            Card(
                backgroundColor = Teal200,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Id: ${book?.id.orEmpty()}")
                    Text(text = "Title: ${book?.title.orEmpty()}")
                    Text(text = "Author: ${book?.author.orEmpty()}")
                    Text(text = "Pages: ${book?.pages ?: ""}")
                }
            }
            if (showErrorSomethingWrong) {
                OneButtonDialog({})
//                AlertDialog(
//                    onDismissRequest = { },
//                    confirmButton = {
//                        TextButton(onClick = {
//                            onErrorDialog.invoke()
//                        })
//                        { Text(text = "OK") }
//                    },
//                    dismissButton = {
//                        TextButton(onClick = {
//                            onErrorDialog.invoke()
//                        })
//                        { Text(text = "Cancel") }
//                    },
//                    title = { Text(text = "Please confirm") },
//                    text = { Text(text = "Should I continue with the requested action?") }
//                )
//                Dialog(onDismissRequest = { /*TODO*/ }, content = Text(text = "fdfd"))
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
fun dBookDetailScreenPreview() {
    val mockBookModel = BookModel(
        id = "0",
        title = "title 0",
        author = "author 0",
        pages = 10
    )
    BookDetailScreen(mockBookModel, false, {})
}