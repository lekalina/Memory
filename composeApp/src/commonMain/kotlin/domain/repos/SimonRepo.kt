package domain.repos

import domain.source.LocalSource

class SimonRepo(
    private val local: LocalSource
) {
    val bestScore = local.simonBestScoreFlow

    suspend fun updateBestScore(score: Int) = local.updateSimonBestScore(score)
}