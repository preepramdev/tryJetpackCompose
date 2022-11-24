package com.pram.bookcompose.presentation.widget.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pram.bookcompose.ui.theme.Teal200

@Composable
fun OneButtonDialog(
    onDismiss: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {

    val isDialogShowing = remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = {
            isDialogShowing.value = false
            onDismiss.invoke()
        },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        Card(
            backgroundColor = Teal200,
            modifier = Modifier.width(250.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Title")
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                    isDialogShowing.value = false
                    onButtonClick.invoke()
                }
                ) {
                    Text(text = "Ok")
                }
            }
        }
    }
}