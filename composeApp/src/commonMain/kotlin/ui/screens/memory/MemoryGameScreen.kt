package ui.screens.memory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.MemoryCard
import data.models.MemoryDeck
import data.models.MemoryLevel
import org.koin.compose.koinInject
import ui.common.pxToDp
import ui.theme.CardCornerRadius
import ui.theme.CardElevation
import ui.theme.CardFontSize
import ui.theme.CardToCardMargin
import ui.theme.LightGray
import ui.theme.StandardMargin
import ui.theme.StandardToolbarHeight
import ui.theme.White

@Composable
fun MemoryGameScreen(
    vm: MemoryGameVM = koinInject()
) {
    val level by vm.level.collectAsState()
    val deck by vm.deck.collectAsState()
    val cards by vm.game.cards.collectAsState()
    val allMatched by vm.game.allMatched.collectAsState()
    val flipCount by vm.game.flipCount.collectAsState()
    val bestEasy by vm.bestScoreEasy.collectAsState(null)
    val bestMedium by vm.bestScoreMedium.collectAsState(null)
    val bestHard by vm.bestScoreHard.collectAsState(null)

    var cardWidth by remember { mutableStateOf(0.dp) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(StandardMargin)
    ) {
        val minSize = (if(minWidth < minHeight) minWidth else minHeight)
        val density = LocalDensity.current
        val cardSize = (minSize / level.root) - StandardMargin
        MemoryLevels(level) { vm.changeLevel(it) }
        LazyVerticalGrid(
            modifier = Modifier.padding(top = StandardToolbarHeight),
            columns = GridCells.Adaptive(minSize = cardSize)
        ) {
            itemsIndexed(cards) { index, card ->
                MemoryGameCard(
                    modifier = Modifier
                        .padding(CardToCardMargin)
                        .onGloballyPositioned {
                            cardWidth = it.size.width.pxToDp(density).dp
                        }
                        .height(cardWidth),
                    card = card,
                    deck = deck,
                    onClick = {
                        vm.game.chooseCard(index)
                    }
                )
            }
        }
        Row(Modifier.fillMaxWidth().offset(y = maxWidth + StandardMargin + StandardToolbarHeight)) {
            Text(
                text = "Flips: $flipCount",
                modifier = Modifier.weight(0.5f).fillMaxWidth(),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Start
            )
            Text(
                text = "Best Score: ${when(level) {
                    MemoryLevel.EASY -> bestEasy
                    MemoryLevel.MEDIUM -> bestMedium
                    MemoryLevel.HARD -> bestHard
                } ?: "N/A"}",
                modifier = Modifier.weight(0.5f).fillMaxWidth(),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.End
            )
        }
        if (allMatched) {
            Column(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = StandardToolbarHeight + StandardMargin),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { vm.game.newGame() }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                )) {
                    Text(text = "New Game", color = MaterialTheme.colors.onSurface)
                }
                TextButton(onClick = { vm.setRandomTheme() }) {
                    Text(text = "Play Random Theme", color = MaterialTheme.colors.onBackground)
                }
            }
            vm.updateBestScore(
                currentBest = when(level) {
                    MemoryLevel.EASY -> bestEasy
                    MemoryLevel.MEDIUM -> bestMedium
                    MemoryLevel.HARD -> bestHard
                },
                flipCount = flipCount,
                level = level
            )
        }
    }
}

@Composable
fun MemoryLevels(
    currentLevel: MemoryLevel,
    onClick: (MemoryLevel) -> Unit
) {
    Row(Modifier.fillMaxWidth().height(StandardToolbarHeight)) {
        MemoryLevelButton(
            modifier = Modifier.weight(0.33f).fillMaxWidth(),
            isSelected = currentLevel == MemoryLevel.EASY,
            level = MemoryLevel.EASY,
            onClick = { onClick(MemoryLevel.EASY) }
        )
        MemoryLevelButton(
            modifier = Modifier.weight(0.33f).fillMaxWidth().padding(start = CardToCardMargin, end = CardToCardMargin),
            isSelected = currentLevel == MemoryLevel.MEDIUM,
            level = MemoryLevel.MEDIUM,
            onClick = { onClick(MemoryLevel.MEDIUM) }
        )
        MemoryLevelButton(
            modifier = Modifier.weight(0.33f).fillMaxWidth(),
            isSelected = currentLevel == MemoryLevel.HARD,
            level = MemoryLevel.HARD,
            onClick = { onClick(MemoryLevel.HARD) }
        )
    }
}

@Composable
fun MemoryLevelButton(
    modifier: Modifier,
    isSelected: Boolean,
    level: MemoryLevel,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            disabledBackgroundColor = LightGray,
            disabledContentColor = LightGray
        )
    ) {
        Text(text = level.displayName, color = MaterialTheme.colors.onSurface, modifier = Modifier.align(alignment = Alignment.CenterVertically))
    }
}

@Composable
fun MemoryGameCard(
    modifier: Modifier,
    card: MemoryCard,
    deck: MemoryDeck,
    onClick: () -> Unit
) {
    Box(modifier) {
        Card(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
            shape = RoundedCornerShape(CardCornerRadius),
            elevation = CardElevation,
            backgroundColor = if (card.isFaceUp || card.isMatched) MaterialTheme.colors.surface else deck.deckColor
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (card.isFaceUp || card.isMatched) {
                    CardFaceUpContent(faceValue = card.faceValue)
                } else {
                    CardFaceDownContent(deckPrint = deck.deckPrint)
                }
            }
        }
    }
}

@Composable
fun CardFaceUpContent(
    faceValue: String,
) {
    Text(text = faceValue, fontSize = CardFontSize)
}

@Composable
fun CardFaceDownContent(
    deckPrint: String,
) {
    Text(text = deckPrint, fontSize = CardFontSize, color = White, fontWeight = FontWeight.ExtraBold)
}