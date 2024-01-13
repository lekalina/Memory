package ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.models.People
import org.koin.compose.koinInject
import ui.StarWarsVM

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterScreen(
    vm: StarWarsVM = koinInject()
) {
    val uiState by vm.uiState.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            Text(
                text = "Characters",
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
        items(uiState.characters) {
            Card(Modifier.fillMaxWidth().padding(16.dp)) { Character(it) }
        }
    }
}

@Composable
fun Character(
    character: People
) {
    Column(Modifier.padding(16.dp)) {
        Text("Name: ${character.name}")
        Text("Height: ${character.height}")
        Text("Mass: ${character.mass}")
        Text("Hair Color: ${character.hairColor}")
        Text("Eye Color: ${character.eyeColor}")
        Text("Skin Color: ${character.skinColor}")
        Text("Birth Year: ${character.birthYear}")
        Text("Gender: ${character.gender}")
    }
}