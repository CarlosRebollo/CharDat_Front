package ies.quevedo.rpgchardatcompose.data.repository.remote

import ies.quevedo.rpgchardatcompose.data.remote.dataSources.UsuarioRemoteDataSource
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsuarioRemoteRepository @Inject constructor(
    private val usuarioRemoteDataSource: UsuarioRemoteDataSource
) {

    fun getUsuarioByID(idUsuario: String): Flow<NetworkResult<Usuario>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.fetchUsuario(idUsuario))
        }.flowOn(Dispatchers.IO)
    }

    fun getUsuarios(): Flow<NetworkResult<List<Usuario>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.fetchUsuarios())
        }.flowOn(Dispatchers.IO)
    }

    fun registrarUsuario(usuario: Usuario): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.registrarUsuario(usuario))
        }.flowOn(Dispatchers.IO)
    }

    fun loguearUsuario(usuario: Usuario): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.loguearUsuario(usuario))
        }.flowOn(Dispatchers.IO)
    }

    fun deleteUsuario(idUsuario: String): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(usuarioRemoteDataSource.deleteUsuario(idUsuario))
        }.flowOn(Dispatchers.IO)
    }

}