package ies.quevedo.rpgchardatcompose.network

import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.TokenJWT
import ies.quevedo.rpgchardatcompose.domain.Usuario
import retrofit2.Response
import retrofit2.http.*

interface UsuarioService {

    @POST("api/usuarios/")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<ApiResponse>

    @POST("api/usuarios/login")
    suspend fun loguearUsuario(@Body usuario: Usuario): Response<TokenJWT>
}