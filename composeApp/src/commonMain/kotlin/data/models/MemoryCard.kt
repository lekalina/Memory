package data.models

import randomUUID

data class MemoryCard(
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false,
    val identifier: String = randomUUID(),
    val faceValue: String,
)