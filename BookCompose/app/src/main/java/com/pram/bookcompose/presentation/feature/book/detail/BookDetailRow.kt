package com.pram.bookcompose.presentation.feature.book.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BookDetailRow(
    header: String,
    detail: String,
) {
    Row {
        Text(
            text = header,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(3f)
        )
        Text(
            text = detail,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.weight(7f)
        )
    }
}

@Preview
@Composable
fun BookDetailRowPreview() {
    BookDetailRow("Id", "001")
}