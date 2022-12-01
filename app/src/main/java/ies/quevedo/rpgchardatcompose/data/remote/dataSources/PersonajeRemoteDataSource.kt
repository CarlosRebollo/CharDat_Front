package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.network.PersonajeService
import javax.inject.Inject

class PersonajeRemoteDataSource @Inject constructor(
    private val personajeService: PersonajeService
) : BaseApiResponse() {

    suspend fun fetchPersonajes(token: String): NetworkResult<List<Personaje>> {
        return safeApiCall(
            apiCall = { personajeService.getPersonajes(token = token) }
        )
    }

    suspend fun postPersonajes(
        token: String, personajes: List<Personaje>
    ): NetworkResult<ApiResponse> {
        return safeApiCall(
            apiCall = { personajeService.postPersonajes(token, personajes) }
        )
    }
}