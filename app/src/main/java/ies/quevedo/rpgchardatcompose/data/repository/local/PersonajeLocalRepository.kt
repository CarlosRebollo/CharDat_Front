package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toPersonaje
import ies.quevedo.rpgchardatcompose.data.entities.toPersonajeConTodo
import ies.quevedo.rpgchardatcompose.data.entities.toPersonajeEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOPersonaje
import ies.quevedo.rpgchardatcompose.domain.Personaje
import javax.inject.Inject

class PersonajeLocalRepository @Inject constructor(private val daoPersonaje: DAOPersonaje) {

    suspend fun getPersonaje(id: Int): Personaje = daoPersonaje.getPersonaje(id = id).toPersonaje()

    suspend fun getPersonajes(): List<Personaje> =
        daoPersonaje.getPersonajes().map { it.toPersonaje() }

    suspend fun insertPersonaje(personaje: Personaje) =
        daoPersonaje.insertPersonaje(personaje = personaje.toPersonajeEntity())

    suspend fun insertAll(personajes: List<Personaje>) =
        daoPersonaje.insertAll(personajes = personajes.map { it.toPersonajeConTodo() })

    suspend fun updatePersonaje(personaje: Personaje) =
        daoPersonaje.updatePersonaje(personaje = personaje.toPersonajeEntity())

    suspend fun deletePersonaje(personajeConTodo: Personaje) =
        daoPersonaje.deletePersonaje(personajeConTodo = personajeConTodo.toPersonajeConTodo())

    suspend fun deleteAll(personajesConTodo: List<Personaje>) =
        daoPersonaje.deleteAll(personajeConTodo = personajesConTodo.map { it.toPersonajeConTodo() })
}