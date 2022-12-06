package com.pram.bookcompose.presentation.feature.book.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pram.bookcompose.data.BookModel
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun BookItemRow(
    header: String,
    detail: String,
) {
    Row {
        Text(
            text = header,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = detail,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.weight(8f)
        )
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
//            .padding(vertical = 4.dp, horizontal = 4.dp)
            .padding(vertical = 4.dp)
            .clickable(true, onClick = {
                onBookClick?.invoke(book)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            BookItemRow("Id", book.id)
            BookItemRow("Title", book.title)
            BookItemRow("Author", book.author)
            BookItemRow("Pages", "${book.pages}")
        }
    }
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