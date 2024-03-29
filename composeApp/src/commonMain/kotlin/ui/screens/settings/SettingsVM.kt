package ui.screens.settings

import data.models.memory.MemoryDeck
import data.models.memory.getDeckFromThemeKey
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.SettingsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsVM(
    private val repo: SettingsRepo
): ViewModel() {

    val gameThemes = repo.gameThemes
    val bestMemoryScoreEasy = repo.bestMemoryScoreEasy
    val bestMemoryScoreMedium = repo.bestMemoryScoreMedium
    val bestMemoryScoreHard = repo.bestMemoryScoreHard
    val bestSimonScore = repo.bestSimonScore

    val currentGameTheme: Flow<MemoryDeck> = repo.gameTheme.map { it.getDeckFromThemeKey() }

    fun setGameTheme(theme: MemoryDeck) {
        viewModelScope.launch {
            repo.setGameTheme(theme.key)
        }
    }
}