package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.TokenJWT
import ies.quevedo.rpgchardatcompose.domain.Usuario
import ies.quevedo.rpgchardatcompose.network.UsuarioService
import javax.inject.Inject

class UsuarioRemoteDataSource @Inject constructor(
    private val usuarioService: UsuarioService
) : BaseApiResponse() {

    suspend fun registrarUsuario(usuario: Usuario): NetworkResult<ApiResponse> {
        return safeApiCall(
            apiCall = { usuarioService.registrarUsuario(usuario) }
        )
    }

    suspend fun loguearUsuario(usuario: Usuario): NetworkResult<TokenJWT> {
        return safeApiCall(
            apiCall = { usuarioService.loguearUsuario(usuario) }
        )
    }
}