package ies.quevedo.rpgchardatcompose.network

import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import ies.quevedo.rpgchardatcompose.domain.Personaje
import retrofit2.Response
import retrofit2.http.*

interface PersonajeService {

    @GET("api/personajes/{id}")
    suspend fun getPersonajeByID(@Path("id") idPersonaje: Int): Response<Personaje>

    @GET("api/personajes")
    suspend fun getPersonajes(): Response<List<Personaje>>

    @POST("api/personajes")
    suspend fun postPersonaje(@Body personaje: Personaje): Response<Personaje>

    @POST("api/files")
    suspend fun postPersonajes(
        @Header("x-token") token: String,
        @Body personajes: List<Personaje>
    ): Response<ApiResponse>

    @PUT("api/personajes")
    suspend fun putPersonaje(@Body personaje: Personaje): Response<Personaje>

    @DELETE("api/personajes")
    suspend fun deletePersonaje(@Query("idPersonaje") idPersonaje: Int): Response<Personaje>
}