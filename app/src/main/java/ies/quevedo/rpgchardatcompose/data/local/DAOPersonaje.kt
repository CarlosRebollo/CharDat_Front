package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.*

@Dao
interface DAOPersonaje {

    @Transaction
    @Query("SELECT * FROM personaje WHERE id = :id")
    fun getPersonaje(id: Int): PersonajeConTodo?

    @Transaction
    @Query("SELECT * FROM personaje")
    fun getPersonajes(): List<PersonajeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersonaje(personaje: PersonajeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(personajes: List<PersonajeEntity>)

    @Update
    fun updatePersonaje(personaje: PersonajeEntity)

    @Delete
    fun deletePersonaje(
        armaduras: List<ArmaduraEntity>,
        armas: List<ArmaEntity>,
        escudos: List<EscudoEntity>,
        objetos: List<ObjetoEntity>,
        personaje: PersonajeEntity
    )

    @Delete
    fun deleteAll(personajes: List<PersonajeEntity>)
}