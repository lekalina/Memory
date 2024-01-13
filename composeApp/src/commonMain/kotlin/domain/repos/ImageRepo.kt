package domain.repos

import domain.source.LocalSource

class ImageRepo(
    private val localSource: LocalSource
) {
    suspend fun updateCount(count: Int) = localSource.updateExampleCounter(count)
    val clickCount = localSource.exampleCounterFlow
}