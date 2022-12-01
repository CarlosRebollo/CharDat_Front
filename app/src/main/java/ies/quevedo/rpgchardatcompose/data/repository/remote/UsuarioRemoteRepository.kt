package ies.quevedo.rpgchardatcompose.data.repository.remote

import ies.quevedo.rpgchardatcompose.data.remote.dataSources.UsuarioRemoteDataSource
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.TokenJWT
import ies.quevedo.rpgchardatcompose.domain.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsuarioRemoteRepository @Inject constructor(
    private val usuarioRemoteDataSource: UsuarioRemoteDataSource
) {

    fun registrarUsuario(usuario: Usuario): Flow<NetworkResult<ApiResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.registrarUsuario(usuario))
        }.flowOn(Dispatchers.IO)
    }

    fun loguearUsuario(usuario: Usuario): Flow<NetworkResult<TokenJWT>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.loguearUsuario(usuario))
        }.flowOn(Dispatchers.IO)
    }
}