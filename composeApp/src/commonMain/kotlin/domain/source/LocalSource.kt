package domain.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import data.models.memory.MemoryDeck
import data.models.memory.MemoryLevel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class LocalSource(
    private val dataStore: DataStore<Preferences>
) {

    private val gameThemeConfig = stringPreferencesKey("game_them_config")
    private val simonGameBestScore = intPreferencesKey("simon_best_score")
    private val memoryGameBestScoreEasy = intPreferencesKey("memory_best_score_easy")
    private val memoryGameBestScoreMedium = intPreferencesKey("memory_best_score_medium")
    private val memoryGameBestScoreHard = intPreferencesKey("memory_best_score_hard")
    val gameThemes = listOf(
        MemoryDeck.Emotions,
        MemoryDeck.Animals,
        MemoryDeck.Ocean,
        MemoryDeck.Plants,
        MemoryDeck.Birds,
        MemoryDeck.Fruit,
        MemoryDeck.Food,
        MemoryDeck.Weather,
        MemoryDeck.Music,
        MemoryDeck.Sports,
        MemoryDeck.Travel,
        MemoryDeck.Alphabet,
        MemoryDeck.Halloween,
        MemoryDeck.Thanksgiving,
        MemoryDeck.Christmas,
        MemoryDeck.Valentines,
    )

    suspend fun updateGameTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[gameThemeConfig] = theme
        }
    }

    fun getTheme(): String? = runBlocking {
        dataStore.data.map { preferences -> preferences[gameThemeConfig] }.firstOrNull()
    }

    val gameThemeFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[gameThemeConfig] ?: ""
        }

    suspend fun updateMemoryBestScore(score: Int, level: MemoryLevel) {
        val key = when(level) {
            MemoryLevel.EASY -> memoryGameBestScoreEasy
            MemoryLevel.MEDIUM -> memoryGameBestScoreMedium
            MemoryLevel.HARD -> memoryGameBestScoreHard
        }
        dataStore.edit { preferences ->
            preferences[key] = score
        }
    }

    val memoryBestScoreEasyFlow: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[memoryGameBestScoreEasy]
        }

    val memoryBestScoreMediumFlow: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[memoryGameBestScoreMedium]
        }

    val memoryBestScoreHardFlow: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[memoryGameBestScoreHard]
        }

    suspend fun updateSimonBestScore(score: Int) {
        dataStore.edit { preferences ->
            preferences[simonGameBestScore] = score
        }
    }

    val simonBestScoreFlow: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[simonGameBestScore]
        }
}