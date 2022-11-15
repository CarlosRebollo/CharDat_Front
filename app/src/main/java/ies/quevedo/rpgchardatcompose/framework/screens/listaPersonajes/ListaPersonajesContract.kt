package ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ListaPersonajesContract {

    sealed class Event {
        object GetAllPersonajes : Event()
        data class GetPersonajeById(val id: Int) : Event()
        data class InsertPersonaje(val personaje: Personaje) : Event()
        data class DeletePersonaje(val id: Int) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var personaje: Personaje? = null,
        var listaPersonajes: List<Personaje>? = null,
        val isLoading: Boolean = false,
        var error: String? = null
    )
}