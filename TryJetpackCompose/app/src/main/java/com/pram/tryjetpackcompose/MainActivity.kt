package com.pram.tryjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pram.tryjetpackcompose.ui.theme.TryJetpackComposeTheme
import org.w3c.dom.NameList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyScreenContent(
    names: List<String> = List(1000) { "Hello Android $it" },
) {
    val counterState = remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        NamesList(names, modifier = Modifier.weight(1f))
        Counter(
            count = counterState,
            updateCounter = { newCount ->
                counterState.value = newCount
            }
        )
        if (counterState.value > 5) {
            Text(text = "I love to count")
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    TryJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) { content() }
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) {
            Greeting(name = it)
            Divider()
        }
    }
}

@Composable
fun Counter(count: MutableState<Int>, updateCounter: (Int) -> Unit) {
    Button(onClick = { updateCounter(count.value + 1) }) {
        Text(text = "I've been click ${count.value} times")
    }
}

@Composable
fun Greeting(name: String) {
    val isSelected = remember {
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if (isSelected.value)
            MaterialTheme.colors.primary
        else
            Color.Transparent,
        animationSpec = tween(durationMillis = 4000)
    )
    Surface(color = targetColor) {
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .background(color = targetColor)
                .clickable { isSelected.value = isSelected.value.not() }
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}