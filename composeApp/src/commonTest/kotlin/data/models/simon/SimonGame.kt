package data.models.simon

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlin.test.Test
import kotlin.test.assertEquals

class SimonGameTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    val game = SimonGame(scope = TestScope())

    @Test
    fun test() {
        game.newGame()
        val currentPattern = game.getPattern()
        assertEquals(expected = 1, actual = currentPattern.size)
        val isMatch = game.onTapColor(currentPattern[0])
        assertEquals(expected = true, actual = isMatch)

    }
}