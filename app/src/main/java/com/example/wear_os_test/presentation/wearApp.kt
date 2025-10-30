package com.example.wear_os_test.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun WearApp() {
    var counter by remember { mutableStateOf(0) }

    Scaffold(
        timeText = {
            TimeText()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Etwas Abstand zu den Rändern
        ) {

            // 3. Zähler-Text:
            // Wir richten diesen Text am 'TopCenter' (Oben, Mitte) der Box aus.
            Text(
                text = "Klicks: $counter",
                style = MaterialTheme.typography.title3,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // 4. Button-Spalte:
            // Diese Column enthält nur die Buttons und wird als Ganzes
            // im 'Center' (Mitte) der Box ausgerichtet.
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                // verticalArrangement sorgt für Abstand ZWISCHEN den Buttons
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Chip(
                    onClick = { counter++ },
                    label = {
                        Text(
                            text = "Klick mich",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Chip(
                    onClick = { counter = 0 },
                    label = {
                        Text(
                            text = "Reset",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colors.error,
                    )
                )
            }
        }
    }
}


@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}