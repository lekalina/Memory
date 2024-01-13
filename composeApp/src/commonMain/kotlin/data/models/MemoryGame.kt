package data.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

enum class MemoryLevel(val pairs: Int, val total: Int, val root: Int, val displayName: String) {
    EASY(pairs = 2, total = 4, root = 2, displayName = "Easy"),
    MEDIUM(pairs = 8, total = 16, root = 4, displayName = "Medium"),
    HARD(pairs = 18, total = 36, root = 6, displayName = "Advanced")
}

class MemoryGame(deck: MemoryDeck = MemoryDeck.Emotions) {

    private var numberOfCardPairs: Int = MemoryLevel.MEDIUM.pairs
    private var cardDeck: MemoryDeck = deck
    private var _flipCount = 0
    private var _cards = mutableListOf<MemoryCard>()

    val allMatched = MutableStateFlow(false)
    val flipCount = MutableStateFlow(_flipCount)
    val cards = MutableStateFlow(_cards.toList())

    init {
        newGame()
    }

    fun newGame() {
        allMatched.value = false
        _flipCount = 0
        flipCount.value = _flipCount
        _cards = getGamePlayDeck()
        cards.value = _cards
        flipAllCardsDown()
    }

    fun chooseCard(index: Int) {
        val editableCards = _cards.map { it.copy() }.toList()
        if (!editableCards[index].isMatched) {
            val previousCardsInPlay: List<Int> = getIndicesOfCardsInPlay(editableCards)
            _flipCount++
            flipCount.value = _flipCount
            editableCards[index].isFaceUp = true
            val cardsInPlay: List<Int> = getIndicesOfCardsInPlay(editableCards)
            if (cardsInPlay.size == 2) {
                // check for match
                val firstCardIndex = cardsInPlay[0]
                val secondCardIndex = cardsInPlay[1]
                if (editableCards[firstCardIndex].identifier == editableCards[secondCardIndex].identifier) {
                    editableCards[firstCardIndex].isMatched = true
                    editableCards[secondCardIndex].isMatched = true
                    allMatched.value = checkAllMatched(editableCards)
                }
            } else if (cardsInPlay.size > 2) {
                for (cardIndex in previousCardsInPlay) {
                    editableCards[cardIndex].isFaceUp = false
                }
            }
            cards.value = editableCards
            _cards = editableCards.toMutableList()
        }
    }

    fun updateLevel(level: MemoryLevel) {
        numberOfCardPairs = level.pairs
        newGame()
    }

    fun updateDeck(deck: MemoryDeck) {
        cardDeck = deck
        newGame()
    }

    private fun flipAllCardsDown() {
        for (card in _cards) {
            card.isFaceUp = false
            card.isMatched = false
        }
        cards.value = _cards
    }

    private fun getIndicesOfCardsInPlay(list: List<MemoryCard>): List<Int> {
        val cardsInPlay: MutableList<Int> = arrayListOf()
        list.forEachIndexed { index, card ->
            if (card.isFaceUp && !card.isMatched) {
                cardsInPlay.add(index)
            }
        }
        return cardsInPlay
    }

    private fun checkAllMatched(list: List<MemoryCard>): Boolean {
        for (card in list) {
            if (!card.isMatched) {
                return false
            }
        }
        return true
    }

    private fun getGamePlayDeck(): ArrayList<MemoryCard> {
        val tempDeck: ArrayList<String> = ArrayList<String>().apply { cardDeck.cards.map { it }.toCollection(this) }
        val gamePlayCards = arrayListOf<MemoryCard>()
        for (i in 0 until numberOfCardPairs) {
            val tempDeckIndex = Random.Default.nextInt(tempDeck.size)
            val card = MemoryCard(faceValue = tempDeck[tempDeckIndex])
            gamePlayCards.add(card)
            val cardMatch = card.copy()
            gamePlayCards.add(cardMatch)
            tempDeck.removeAt(tempDeckIndex)
        }
        gamePlayCards.shuffle()
        return gamePlayCards
    }
}