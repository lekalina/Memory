package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class People(
    @SerialName("birth_year") val birthYear: String? = null,
    val created: String? = null,
    val edited: String? = null,
    @SerialName("eye_color") val eyeColor: String? = null,
    val films: List<String>? = null,
    val gender: String? = null,
    @SerialName("hair_color") val hairColor: String? = null,
    val height: String? = null,
    val homeworld: String? = null,
    val mass: String? = null,
    val name: String? = null,
    @SerialName("skin_color") val skinColor: String? = null,
    val species: List<String>? = null,
    val starships: List<String>? = null,
    val url: String? = null,
    val vehicles: List<String>? = null
)