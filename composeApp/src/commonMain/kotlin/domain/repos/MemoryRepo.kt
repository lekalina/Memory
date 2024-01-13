package domain.repos

import data.models.MemoryDeck
import data.models.MemoryLevel
import data.models.getDeckFromThemeKey
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