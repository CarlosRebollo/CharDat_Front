package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.*

@Dao
interface DAOPersonaje {

    @Query("SELECT * FROM personaje WHERE id = :id")
    suspend fun getPersonaje(id: Int): PersonajeEntity

    @Query("SELECT * FROM personaje")
    suspend fun getPersonajes(): List<PersonajeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonaje(personaje: PersonajeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(personajes: List<PersonajeEntity>)

    @Update
    suspend fun updatePersonaje(personaje: PersonajeEntity)

    @Delete
    suspend fun deletePersonaje(personaje: PersonajeEntity)

    @Delete
    suspend fun deleteAll(personajes: List<PersonajeEntity>)
}