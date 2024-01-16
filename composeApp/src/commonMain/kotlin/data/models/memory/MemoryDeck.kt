package data.models.memory

import androidx.compose.ui.graphics.Color
import ui.theme.AnimalGreen
import ui.theme.BirdBlue
import ui.theme.ChristmasRed
import ui.theme.EmojiYellow
import ui.theme.FireEngineRed
import ui.theme.FoodOrange
import ui.theme.FruitPurple
import ui.theme.HalloweenOrange
import ui.theme.MemoryBlue
import ui.theme.MusicBlue
import ui.theme.OceanBlue
import ui.theme.PlantGreen
import ui.theme.ThanksgivingBrown
import ui.theme.ValentinePink
import ui.theme.WeatherGray

sealed class MemoryDeck(val cards: List<String>, val deckColor: Color, val deckPrint: String, val key: String, val displayName: String) {
    data object Emotions: MemoryDeck(cards = emotions, deckColor = EmojiYellow, deckPrint = "☺︎︎", key = "emotions", displayName = "Emotions")
    data object Animals: MemoryDeck(cards = animals, deckColor = AnimalGreen, deckPrint = "♘", key = "animals", displayName = "Animals")
    data object Ocean: MemoryDeck(cards = ocean, deckColor = OceanBlue, deckPrint = "⚓︎︎", key = "ocean", displayName = "Ocean")
    data object Plants: MemoryDeck(cards = plants, deckColor = PlantGreen, deckPrint = "☘︎", key = "plants", displayName = "Plants")
    data object Birds: MemoryDeck(cards = birds, deckColor = BirdBlue, deckPrint = "▻", key = "birds", displayName = "Birds")
    data object Fruit: MemoryDeck(cards = fruit, deckColor = FruitPurple, deckPrint = "❧", key = "fruit", displayName = "Fruit")
    data object Food: MemoryDeck(cards = food, deckColor = FoodOrange, deckPrint = "♨︎", key = "food", displayName = "Food")
    data object Weather: MemoryDeck(cards = weather, deckColor = WeatherGray, deckPrint = "☂︎", key = "weather", displayName = "Weather")
    data object Music: MemoryDeck(cards = music, deckColor = MusicBlue, deckPrint = "♫", key = "music", displayName = "Music")
    data object Sports: MemoryDeck(cards = sports, deckColor = AnimalGreen, deckPrint = "⛳︎", key = "sports", displayName = "Sports")
    data object Travel: MemoryDeck(cards = travel, deckColor = FireEngineRed, deckPrint = "⛽︎", key = "travel", displayName = "Travel")
    data object Alphabet: MemoryDeck(cards = alphabet, deckColor = MemoryBlue, deckPrint = "Æ", key = "alphabet", displayName = "Alphabet")
    data object Halloween: MemoryDeck(cards = halloween, deckColor = HalloweenOrange, deckPrint = "☠", key = "halloween", displayName = "Halloween")
    data object Thanksgiving: MemoryDeck(cards = thanksgiving, deckColor = ThanksgivingBrown, deckPrint = "★", key = "thanksgiving", displayName = "Thanksgiving")
    data object Christmas: MemoryDeck(cards = christmas, deckColor = ChristmasRed, deckPrint = "☃︎", key = "christmas", displayName = "Christmas")
    data object Valentines: MemoryDeck(cards = valentines, deckColor = ValentinePink, deckPrint = "♥︎", key = "valentines", displayName = "Valentines")
}

fun String?.getDeckFromThemeKey(): MemoryDeck {
    return when(this) {
        MemoryDeck.Emotions.key -> MemoryDeck.Emotions
        MemoryDeck.Animals.key -> MemoryDeck.Animals
        MemoryDeck.Ocean.key -> MemoryDeck.Ocean
        MemoryDeck.Plants.key -> MemoryDeck.Plants
        MemoryDeck.Birds.key -> MemoryDeck.Birds
        MemoryDeck.Fruit.key -> MemoryDeck.Fruit
        MemoryDeck.Food.key -> MemoryDeck.Food
        MemoryDeck.Weather.key -> MemoryDeck.Weather
        MemoryDeck.Music.key -> MemoryDeck.Music
        MemoryDeck.Sports.key -> MemoryDeck.Sports
        MemoryDeck.Travel.key -> MemoryDeck.Travel
        MemoryDeck.Alphabet.key -> MemoryDeck.Alphabet
        MemoryDeck.Halloween.key -> MemoryDeck.Halloween
        MemoryDeck.Thanksgiving.key -> MemoryDeck.Thanksgiving
        MemoryDeck.Christmas.key -> MemoryDeck.Christmas
        MemoryDeck.Valentines.key -> MemoryDeck.Valentines
        else -> MemoryDeck.Emotions
    }
}
