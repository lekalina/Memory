package data.models

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<People>? = null
)