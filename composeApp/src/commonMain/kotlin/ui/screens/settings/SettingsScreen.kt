package ui.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import data.models.memory.MemoryDeck
import data.models.memory.MemoryLevel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import ui.navigation.NavRoute
import ui.navigation.NavigationVM
import ui.theme.BorderWidth
import ui.theme.CardCornerRadius
import ui.theme.CardElevation
import ui.theme.HalfMargin
import ui.theme.IconSize
import ui.theme.LightGray
import ui.theme.StandardMargin
import ui.theme.StandardToolbarHeight
import ui.theme.White

@Composable
fun SettingsScreen(
    vm: SettingsVM = koinInject(),
    nav: NavigationVM
) {
    val currentGameTheme by vm.currentGameTheme.collectAsState(MemoryDeck.Emotions)
    val bestEasy by vm.bestScoreEasy.collectAsState(null)
    val bestMedium by vm.bestScoreMedium.collectAsState(null)
    val bestHard by vm.bestScoreHard.collectAsState(null)
    LazyColumn(Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
        item {
            Text(
                modifier = Modifier.padding(start = StandardMargin, top = StandardMargin),
                text = "Game Theme",
                color = MaterialTheme.colors.onBackground
            )
        }
        item {
            Box(Modifier.fillMaxWidth().clickable { nav.navigate(NavRoute.GameThemes) }) {
                GameThemeRow(theme = currentGameTheme)
            }
        }
        item {
            Text(
                modifier = Modifier.padding(start = StandardMargin, top = StandardMargin),
                text = "Game Stats",
                color = MaterialTheme.colors.onBackground
            )
        }
        item {
            Box(Modifier.fillMaxWidth().clickable { nav.navigate(NavRoute.GameThemes) }) {
                GameStats(bestEasy, bestMedium, bestHard)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GameStats(
    bestEasy: Int?,
    bestMedium: Int?,
    bestHard: Int?
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            start = StandardMargin,
            end = StandardMargin,
            top = HalfMargin,
            bottom = HalfMargin
        ),
        elevation = CardElevation,
        shape = RoundedCornerShape(CardCornerRadius),
    ) {
        Column(Modifier.fillMaxWidth().padding(StandardMargin)) {
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(res = "ic_memory.xml"),
                    contentDescription = "memory icon",
                    tint = MaterialTheme.colors.primaryVariant
                )
                Text(
                    modifier = Modifier.padding(start = StandardMargin),
                    text = "Best Score",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Divider(Modifier.fillMaxWidth().padding(top = HalfMargin, bottom = HalfMargin), color = LightGray)
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = MemoryLevel.EASY.displayName,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = MemoryLevel.MEDIUM.displayName,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = MemoryLevel.HARD.displayName,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = "${bestEasy ?: "N/A"}",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = "${bestMedium ?: "N/A"}",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    modifier = Modifier.weight(0.33f).fillMaxWidth(),
                    text = "${bestHard ?: "N/A"}",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun GameThemeRow(
    borderColor: Color = Color.Transparent,
    theme: MemoryDeck
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            start = StandardMargin,
            end = StandardMargin,
            top = HalfMargin,
            bottom = HalfMargin
        ),
        elevation = CardElevation,
        shape = RoundedCornerShape(CardCornerRadius),
        border = BorderStroke(width = BorderWidth, color = borderColor)
    ) {
        Row(Modifier.fillMaxWidth().padding(StandardMargin)) {
            Box(Modifier
                .size(IconSize)
                .background(
                    color = theme.deckColor,
                    shape = RoundedCornerShape(CardCornerRadius)
                )
            ) {
                Text(
                    text = theme.deckPrint,
                    modifier = Modifier.align(Alignment.Center),
                    color = White
                )
            }
            Text(
                modifier = Modifier.padding(start = StandardMargin),
                text = theme.displayName,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun ChangeGameThemeScreen(
    vm: SettingsVM = koinInject(),
    nav: NavigationVM
) {
    val currentGameTheme by vm.currentGameTheme.collectAsState(MemoryDeck.Emotions)
    LazyColumn(Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
        stickyHeader {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(StandardToolbarHeight)
                .background(color = MaterialTheme.colors.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { nav.back() }) {
                    Icon(
                        painter = painterResource(res = "ic_back.xml"),
                        contentDescription = "back",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
                Text(
                    text = "Default Theme",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = StandardMargin),
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
        items(vm.gameThemes) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { vm.setGameTheme(it) }
            ) {
                GameThemeRow(
                    borderColor = if (it.key == currentGameTheme.key) it.deckColor else Color.Transparent,
                    theme = it
                )
            }
        }
        item {
            Box(Modifier.fillMaxWidth().height(StandardToolbarHeight))
        }
    }
}