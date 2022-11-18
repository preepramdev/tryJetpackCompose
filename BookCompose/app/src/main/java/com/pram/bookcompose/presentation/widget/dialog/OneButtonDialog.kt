package com.pram.bookcompose.presentation.widget.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun OneButtonDialog(
    onDismissRequest: () -> Unit
) {

    Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties(
        dismissOnClickOutside = true,
        dismissOnBackPress = true
    )) {
        Card(backgroundColor = Teal200) {
            Column {
                Text(text = "Title")
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Ok")
                }
            }
        }
    }
}