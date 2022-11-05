package ies.quevedo.rpgchardatcompose.framework.screens.listaPersonajes

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ListaPersonajesContract {

    sealed class Event {
        object FetchPersonajes : Event()
        data class FetchPersonaje(val id: Int) : Event()
        data class PostPersonaje(val personaje: Personaje) : Event()
        data class DeletePersonaje(val id: Int) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var personaje: Personaje? = null,
        val personajeBorrado: Personaje? = null,
        val personajeRecuperado: Personaje? = null,
        var listaPersonajes: List<Personaje>? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}