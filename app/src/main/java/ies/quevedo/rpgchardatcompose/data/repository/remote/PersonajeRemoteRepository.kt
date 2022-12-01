package ies.quevedo.rpgchardatcompose.data.repository.remote

import ies.quevedo.rpgchardatcompose.data.remote.dataSources.PersonajeRemoteDataSource
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonajeRemoteRepository @Inject constructor(
    private val personajeRemoteDataSource: PersonajeRemoteDataSource
) {

    fun getPersonajes(token: String): Flow<NetworkResult<List<Personaje>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(personajeRemoteDataSource.fetchPersonajes(token = token))
        }.flowOn(Dispatchers.IO)
    }

    fun postPersonajes(
        token: String,
        personajes: List<Personaje>
    ): Flow<NetworkResult<ApiResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(personajeRemoteDataSource.postPersonajes(token, personajes))
        }.flowOn(Dispatchers.IO)
    }
}