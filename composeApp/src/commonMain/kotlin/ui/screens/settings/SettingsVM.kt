package ui.screens.settings

import data.models.MemoryDeck
import data.models.getDeckFromThemeKey
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.SettingsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsVM(
    private val repo: SettingsRepo
): ViewModel() {

    val gameThemes = repo.gameThemes
    val bestScoreEasy = repo.bestScoreEasy
    val bestScoreMedium = repo.bestScoreMedium
    val bestScoreHard = repo.bestScoreHard

    val currentGameTheme: Flow<MemoryDeck> = repo.gameTheme.map { it.getDeckFromThemeKey() }

    fun setGameTheme(theme: MemoryDeck) {
        viewModelScope.launch {
            repo.setGameTheme(theme.key)
        }
    }
}