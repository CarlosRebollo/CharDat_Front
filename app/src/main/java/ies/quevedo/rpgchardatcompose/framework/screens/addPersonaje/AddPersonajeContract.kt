package ies.quevedo.rpgchardatcompose.framework.screens.addPersonaje

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface AddPersonajeContract {

    sealed class Event {
        data class AddPersonaje(val personaje: Personaje) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val error: String? = null
    )
}