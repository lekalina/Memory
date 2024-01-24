package domain.repos

import data.models.memory.MemoryDeck
import data.models.memory.MemoryLevel
import data.models.memory.getDeckFromThemeKey
import domain.source.LocalSource

class MemoryRepo(
    private val local: LocalSource
) {

    val themedDecks = local.gameThemes

    val bestScoreEasy = local.memoryBestScoreEasyFlow
    val bestScoreMedium = local.memoryBestScoreMediumFlow
    val bestScoreHard = local.memoryBestScoreHardFlow

    fun getDefaultDeck(): MemoryDeck {
        return local.getTheme().getDeckFromThemeKey()
    }

    suspend fun updateBestScore(score: Int, level: MemoryLevel) = local.updateMemoryBestScore(score, level)
}