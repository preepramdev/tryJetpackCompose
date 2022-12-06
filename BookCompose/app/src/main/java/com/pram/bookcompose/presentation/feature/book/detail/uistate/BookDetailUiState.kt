package com.pram.bookcompose.presentation.feature.book.detail.uistate

sealed class BookDetailErrorUiState {

    object SomethingWrongError : BookDetailErrorUiState()
    object NoInternetError : BookDetailErrorUiState()
}
