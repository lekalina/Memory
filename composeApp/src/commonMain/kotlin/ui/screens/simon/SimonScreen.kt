package ui.screens.simon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import data.models.simon.SimonColor
import org.koin.compose.koinInject
import ui.theme.CardCornerRadius
import ui.theme.CardElevation
import ui.theme.HalfMargin
import ui.theme.SimonBlueBright
import ui.theme.SimonBlueDark
import ui.theme.SimonCardSize
import ui.theme.SimonGreenBright
import ui.theme.SimonGreenDark
import ui.theme.SimonRedBright
import ui.theme.SimonRedDark
import ui.theme.SimonYellowBright
import ui.theme.SimonYellowDark
import ui.theme.StandardMargin
import ui.theme.StandardToolbarHeight

@Composable
fun SimonScreen(
    vm: SimonVM = koinInject()
) {
    val gameOver by vm.game.gameOver.collectAsState()
    val activeColor by vm.game.activeColor.collectAsState()
    val score by vm.game.score.collectAsState()
    val bestScore by vm.bestScore.collectAsState(null)

    Box(Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(StandardMargin),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                SimonColorButton(
                    isActive = activeColor == SimonColor.GREEN,
                    dimColor = SimonGreenDark,
                    brightColor = SimonGreenBright
                ) { if (!gameOver) { vm.onClick(SimonColor.GREEN) }}
                SimonColorButton(
                    isActive = activeColor == SimonColor.RED,
                    dimColor = SimonRedDark,
                    brightColor = SimonRedBright
                ) { if (!gameOver) { vm.onClick(SimonColor.RED) }}
            }
            Row {
                SimonColorButton(
                    isActive = activeColor == SimonColor.YELLOW,
                    dimColor = SimonYellowDark,
                    brightColor = SimonYellowBright
                ) { if (!gameOver) { vm.onClick(SimonColor.YELLOW) }}
                SimonColorButton(
                    isActive = activeColor == SimonColor.BLUE,
                    dimColor = SimonBlueDark,
                    brightColor = SimonBlueBright
                ) { if (!gameOver) { vm.onClick(SimonColor.BLUE) }}
            }
            Row(Modifier.fillMaxWidth().padding(top = StandardMargin)) {
                Text(
                    text = "Score: $score",
                    modifier = Modifier.weight(0.5f).fillMaxWidth(),
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Best Score: ${bestScore ?: "N/A"}",
                    modifier = Modifier.weight(0.5f).fillMaxWidth(),
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                )
            }
        }
        if (gameOver) {
            vm.updateBestScore(score = score, currentBest = bestScore)
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = StandardToolbarHeight + StandardMargin),
                onClick = { vm.newGame() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                )
            ) {
                Text("Start", color = MaterialTheme.colors.onSurface)
            }
        }
    }
}

@Composable
fun SimonColorButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    dimColor: Color,
    brightColor: Color,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Card(
        modifier = modifier
            .size(SimonCardSize)
            .padding(HalfMargin)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) { onClick() },
        shape = RoundedCornerShape(CardCornerRadius),
        backgroundColor = if (isActive || isPressed) brightColor else dimColor,
        elevation = CardElevation
    ) {}
}