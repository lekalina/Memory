package ui.screens.simon

import data.models.simon.SimonColor
import data.models.simon.SimonGame
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.repos.SimonRepo
import kotlinx.coroutines.launch

class SimonVM(
    private val repo: SimonRepo
): ViewModel() {

    val game = SimonGame(viewModelScope)
    val bestScore = repo.bestScore

    fun newGame() = game.newGame()

    fun onClick(color: SimonColor) = game.onTapColor(color)

    fun updateBestScore(score: Int, currentBest: Int?) {
        if ((currentBest == null && score > 0) || (score > 0 && score > (currentBest ?: 0))) {
            viewModelScope.launch {
                repo.updateBestScore(score)
            }
        }
    }
}