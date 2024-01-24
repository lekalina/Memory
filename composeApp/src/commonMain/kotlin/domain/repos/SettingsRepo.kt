package domain.repos

import domain.source.LocalSource

class SettingsRepo(
    private val local: LocalSource
) {
    val gameThemes = local.gameThemes

    suspend fun setGameTheme(theme: String) = local.updateGameTheme(theme)

    fun getGameTheme(): String? = local.getTheme()

    val gameTheme = local.gameThemeFlow

    val bestMemoryScoreEasy = local.memoryBestScoreEasyFlow
    val bestMemoryScoreMedium = local.memoryBestScoreMediumFlow
    val bestMemoryScoreHard = local.memoryBestScoreHardFlow
    val bestSimonScore = local.simonBestScoreFlow
}