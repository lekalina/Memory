package data.models.simon

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class SimonGame(
    private val scope: CoroutineScope
) {

    private val simonColors = listOf(
        SimonColor.GREEN,
        SimonColor.RED,
        SimonColor.YELLOW,
        SimonColor.BLUE
    )
    private var _pattern = mutableListOf<SimonColor>()
    private var _score = 0
    private var clickCount = 0
    private val patternDelayTime: Long = 1000 // ms
    private val breakDelayTime: Long = 500 // ms

    val gameOver = MutableStateFlow(true)
    val score = MutableStateFlow(_score)
    val activeColor = MutableStateFlow<SimonColor?>(null)

    fun newGame() {
        _pattern = mutableListOf()
        clickCount = 0
        gameOver.value = false
        _score = 0
        score.value = _score
        updatePattern()
    }

    fun getPattern(): List<SimonColor> = _pattern

    fun onTapColor(color: SimonColor): Boolean {
        val isMatch = _pattern[clickCount] == color
        if (isMatch) {
            if (clickCount < (_pattern.size - 1)) {
                clickCount++
            } else {
                clickCount = 0
                _score++
                score.value = _score
                updatePattern()
            }
        } else {
            clickCount = 0
            gameOver.value = true
        }
        return isMatch
    }

    private fun runPattern() {
        scope.launch {
            delay(patternDelayTime)
            _pattern.forEach {
                activeColor.value = it
                delay(patternDelayTime)
                activeColor.value = null
                delay(breakDelayTime)
            }
            activeColor.value = null
        }
    }

    private fun updatePattern() {
        val nextColor = Random.Default.nextInt(simonColors.size)
        _pattern.add(simonColors[nextColor])
        runPattern()
    }
}