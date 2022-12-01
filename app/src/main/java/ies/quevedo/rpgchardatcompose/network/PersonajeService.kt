package ies.quevedo.rpgchardatcompose.network

import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.Personaje
import retrofit2.Response
import retrofit2.http.*

interface PersonajeService {

    @GET("api/files")
    suspend fun getPersonajes(@Header("x-token") token: String): Response<List<Personaje>>

    @POST("api/files")
    suspend fun postPersonajes(
        @Header("x-token") token: String, @Body personajes: List<Personaje>
    ): Response<ApiResponse>
}