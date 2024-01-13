package ui

import data.models.People
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.StarWarsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CharactersUiState(
    val characters: List<People> = emptyList()
)

class StarWarsVM(
    private val repo: StarWarsRepo
): ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            repo.getCharacters()?.let { characters ->
                _uiState.update { it.copy(characters = characters) }
            }
        }
    }
}