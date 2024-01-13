package domain.repos

import data.helpers.CHARACTERS
import data.helpers.NetworkCreator
import data.helpers.handleRequest
import data.models.Characters
import data.models.People
import io.ktor.client.call.body
import io.ktor.client.request.get

class StarWarsRepo(
    networkCreator: NetworkCreator
) {
    private val httpClient = networkCreator.getHttpClient()

    private suspend fun charactersRequest() = handleRequest {
        httpClient
            .get(CHARACTERS)
            .body<Characters>()
    }

    suspend fun getCharacters(): List<People>? {
        val response = charactersRequest()
        println("TESTING: response = $response")
        return response?.results
    }
}