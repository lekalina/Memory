package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getPlatform


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Kotlin Multi-platform",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "You are viewing this application on an ${getPlatform().name} device",
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}