package com.pram.bookcompose.presentation.feature.book.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pram.bookcompose.data.BookModel
import com.pram.bookcompose.presentation.feature.book.detail.uistate.BookDetailErrorUiState

class BookDetailViewModel : ViewModel() {

    val book: MutableState<BookModel?> = mutableStateOf(null)
    val showError: MutableState<BookDetailErrorUiState?> = mutableStateOf(null)

    fun getBook(bookId: String?) {
        bookId?.let {
            book.value = getMockBook(bookId)
        } ?: run {
            showError(BookDetailErrorUiState.SomethingWrongError)
        }
    }

    private fun showError(bookDetailErrorUiState: BookDetailErrorUiState) {
        showError.value = bookDetailErrorUiState
    }

    fun hideDialog() {
        showError.value = null
    }

    private fun getMockBook(bookId: String): BookModel {
        return BookModel(
            id = bookId,
            title = "title $bookId",
            author = "author $bookId",
            pages = 10 * bookId.toInt()
        )
    }
}