package ies.quevedo.rpgchardatcompose.framework.screens.personajes.showPersonaje

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ShowPersonajeContract {

    sealed class Event {
        data class GetPersonaje(val id: Int) : Event()
        data class UpdatePersonaje(val personaje: Personaje) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val personaje: Personaje? = null,
        val error: String? = null
    )
}