package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ListaPersonajesContract {

    sealed class Event {
        object GetAllPersonajes : Event()
        data class GetPersonajeById(val idPersonaje: Int) : Event()
        data class DeletePersonaje(val personaje: Personaje) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var personaje: Personaje? = null,
        var listaPersonajes: List<Personaje>? = null,
        val isLoading: Boolean = false,
        var error: String? = null
    )
}