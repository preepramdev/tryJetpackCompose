package com.pram.bookcompose.presentation.feature.book.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pram.bookcompose.data.BookModel

class BookDetailViewModel : ViewModel() {

    var isError = true

    val book: MutableState<BookModel?> = mutableStateOf(null)
    val showErrorSomethingWrong: MutableState<Boolean> = mutableStateOf(false)

//    fun getBook(bookId: String?) {
//        bookId?.let {
//            book.value = getMockBook(bookId)
//        } ?: run {
//
//        }
//    }

    fun showDialog() {
        showErrorSomethingWrong.value = true
    }

    fun hideDialog() {
        isError = false
        showErrorSomethingWrong.value = false
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