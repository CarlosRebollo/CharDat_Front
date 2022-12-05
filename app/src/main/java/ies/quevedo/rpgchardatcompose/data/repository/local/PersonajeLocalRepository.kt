package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toPersonaje
import ies.quevedo.rpgchardatcompose.data.entities.toPersonajeEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOPersonaje
import ies.quevedo.rpgchardatcompose.domain.Personaje
import javax.inject.Inject

class PersonajeLocalRepository @Inject constructor(private val daoPersonaje: DAOPersonaje) {

    suspend fun getPersonaje(idPersonaje: Int): Personaje = daoPersonaje.getPersonaje(id = idPersonaje).toPersonaje()

    suspend fun getPersonajes(): List<Personaje> =
        daoPersonaje.getPersonajes().map { it.toPersonaje() }

    suspend fun insertPersonaje(personaje: Personaje) =
        daoPersonaje.insertPersonaje(personaje = personaje.toPersonajeEntity())

    suspend fun updatePersonaje(personaje: Personaje) =
        daoPersonaje.updatePersonaje(personaje = personaje.toPersonajeEntity())

    suspend fun deletePersonaje(personaje: Personaje) =
        daoPersonaje.deletePersonaje(personaje = personaje.toPersonajeEntity())

    suspend fun deleteAllPersonajes() = daoPersonaje.deleteAllPersonajes()
}