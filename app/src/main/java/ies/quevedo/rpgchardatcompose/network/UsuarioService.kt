package ies.quevedo.rpgchardatcompose.network

import ies.quevedo.rpgchardatcompose.domain.Usuario
import retrofit2.Response
import retrofit2.http.*

interface UsuarioService {

    @GET("api/usuarios/{id}")
    suspend fun getUsuarioByID(@Path("id") idUsuario: String): Response<Usuario>

    @GET("api/usuarios/")
    suspend fun getUsuarios(): Response<List<Usuario>>

    @POST("api/usuarios/")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<String>

    @POST("api/usuarios/login")
    suspend fun loguearUsuario(@Body usuario: Usuario): Response<String>

    @PUT("api/usuarios/{id}")
    suspend fun actualizarUsuarioByID(@Path("id") idUsuario: String): Response<String>

    @DELETE("api/usuarios/{id}")
    suspend fun deleteUsuarioByID(@Path("id") idUsuario: String): Response<String>
}