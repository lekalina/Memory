package ui.screens.memory

import data.models.memory.MemoryDeck
import data.models.memory.MemoryGame
import data.models.memory.MemoryLevel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.MemoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MemoryGameVM(
    private val repo: MemoryRepo
): ViewModel() {

    private val defaultDeck = repo.getDefaultDeck()
    val deck: MutableStateFlow<MemoryDeck> = MutableStateFlow(defaultDeck)
    val level: MutableStateFlow<MemoryLevel> = MutableStateFlow(MemoryLevel.MEDIUM)
    val bestScoreEasy = repo.bestScoreEasy
    val bestScoreMedium = repo.bestScoreMedium
    val bestScoreHard = repo.bestScoreHard

    val game = MemoryGame(defaultDeck)

    fun setRandomTheme() {
        val randomIndex = Random.Default.nextInt(repo.themedDecks.size)
        val randomDeck = repo.themedDecks[randomIndex]
        changeGameDeck(randomDeck)
    }

    private fun changeGameDeck(deck: MemoryDeck) {
        this.deck.value = deck
        game.updateDeck(deck)
    }

    fun changeLevel(level: MemoryLevel) {
        this.level.value = level
        game.updateLevel(level)
    }

    fun updateBestScore(currentBest: Int?, flipCount: Int, level: MemoryLevel) {
        if (flipCount > 0) {
            if (currentBest == null || flipCount < currentBest) {
                viewModelScope.launch(Dispatchers.IO) {
                    repo.updateBestScore(flipCount, level)
                }
            }
        }
    }
}