package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Personaje
import ies.quevedo.rpgchardatcompose.network.PersonajeService
import javax.inject.Inject

class PersonajeRemoteDataSource @Inject constructor(
    private val personajeService: PersonajeService
) : BaseApiResponse() {

    suspend fun fetchPersonaje(id: Int): NetworkResult<Personaje> {
        return safeApiCall(
            apiCall = { personajeService.getPersonajeByID(id) }
        )
    }

    suspend fun fetchPersonajes(): NetworkResult<List<Personaje>> {
        return safeApiCall(
            apiCall = { personajeService.getPersonajes() }
        )
    }

    suspend fun postPersonaje(personaje: Personaje): NetworkResult<Personaje> {
        return safeApiCall(
            apiCall = { personajeService.postPersonaje(personaje) }
        )
    }

    suspend fun putPersonaje(personaje: Personaje): NetworkResult<Personaje> {
        return safeApiCall(
            apiCall = { personajeService.putPersonaje(personaje) }
        )
    }

    suspend fun deletePersonaje(idPersonaje: Int): NetworkResult<Personaje> {
        return safeApiCall(
            apiCall = { personajeService.deletePersonaje(idPersonaje) }
        )
    }
}