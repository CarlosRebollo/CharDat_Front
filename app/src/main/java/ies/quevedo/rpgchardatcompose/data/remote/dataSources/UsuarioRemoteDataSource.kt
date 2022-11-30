package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Usuario
import ies.quevedo.rpgchardatcompose.network.UsuarioService
import javax.inject.Inject

class UsuarioRemoteDataSource @Inject constructor(
    private val usuarioService: UsuarioService
) : BaseApiResponse() {

    suspend fun fetchUsuario(idUsuario: String): NetworkResult<Usuario> {
        return safeApiCall(
            apiCall = { usuarioService.getUsuarioByID(idUsuario) }
        )
    }

    suspend fun fetchUsuarios(): NetworkResult<List<Usuario>> {
        return safeApiCall(
            apiCall = { usuarioService.getUsuarios() }
        )
    }

    suspend fun registrarUsuario(usuario: Usuario): NetworkResult<String> {
        return safeApiCall(
            apiCall = { usuarioService.registrarUsuario(usuario) }
        )
    }

    suspend fun loguearUsuario(usuario: Usuario): NetworkResult<String> {
        return safeApiCall(
            apiCall = { usuarioService.loguearUsuario(usuario) }
        )
    }

    suspend fun deleteUsuario(idUsuario: String): NetworkResult<String> {
        return safeApiCall(
            apiCall = { usuarioService.deleteUsuarioByID(idUsuario) }
        )
    }
}