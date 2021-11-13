package com.wotosts.layoutcodelab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.wotosts.layoutcodelab.ui.theme.LayoutCodelabTheme
import kotlinx.coroutines.launch

@Composable
fun BodyContent() {
    val listSize = 100
    val verticalScrollState = rememberLazyListState()

    Column {
        ScrollButtons(listSize = listSize, scrollState = verticalScrollState)
        Spacer(modifier = Modifier.height(16.dp))
        SimpleLazyList(listSize = listSize, verticalScrollState)
    }
}

@Composable
fun ScrollButtons(listSize: Int, scrollState: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            coroutineScope.launch {
                scrollState.scrollToItem(0)
            }
        }) {
            Text(text = "Scroll to the Top")
        }
        Button(onClick = {
            coroutineScope.launch {
                scrollState.scrollToItem(listSize - 1)
            }
        }) {
            Text(text = "Scroll to the Bottom")
        }
    }
}

@Composable
fun ImageListItem(idx: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(text = "Item #$idx", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SimpleLazyList(listSize: Int, scrollState: LazyListState) {
    LazyColumn(state = scrollState) {
        items(listSize) {
            ImageListItem(idx = it)
        }
    }
}

@Composable
fun SimpleList() {
    val verticalScroll = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(verticalScroll)) {
        repeat(100) {
            Text(text = it.toString())
        }
    }
}

@Composable
@Preview
fun SimpleListPreview() {
    LayoutCodelabTheme {
        Scaffold {
            BodyContent()
        }
    }
}
