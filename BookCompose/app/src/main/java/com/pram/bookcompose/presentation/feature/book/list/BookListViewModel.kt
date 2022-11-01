package com.pram.bookcompose.presentation.feature.book.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pram.bookcompose.data.BookModel

class BookListViewModel : ViewModel() {

    val books: MutableState<List<BookModel>> = mutableStateOf(listOf())

    fun getBooks() {
        books.value = mockBookModels
    }

    private val mockBookModels = List(100) { index ->
        BookModel(
            id = index.toString(),
            title = "title $index",
            author = "author $index",
            pages = (index * 10)
        )
    }

    fun performSelectBook(clickedBook: BookModel) {
        Log.e("TAG", "performSelectBook: $clickedBook")
    }

    fun performAddBook() {
        Log.e("TAG", "performAddBook: ", )
    }
}