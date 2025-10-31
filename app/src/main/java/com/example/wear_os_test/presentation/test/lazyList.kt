package com.example.wear_os_test.presentation.test

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import kotlinx.coroutines.launch


@Composable
fun LazyList() {
    val listItems = (1..5).map { "Eintrag Nr. $it" }
    Scaffold(
        timeText = { TimeText() }
    ) {
        val listState = rememberScalingLazyListState()

        val scope = rememberCoroutineScope()

        val focusRequester = remember { FocusRequester() }


        ScalingLazyColumn(
            state = listState,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .onRotaryScrollEvent {
                    scope.launch {
                        listState.scrollBy(it.verticalScrollPixels)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable()

        ) {
            item {
                Text(
                    text = "Menü",
                    style = MaterialTheme.typography.title3
                )
            }

            items(listItems) { aktuellerText ->
                Chip(
                    onClick = {
                        Log.d("LazyList", "Clicked $aktuellerText")
                    },
                    label = {
                        Text(
                            text = aktuellerText,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
private fun Test() {
    LazyList()
}