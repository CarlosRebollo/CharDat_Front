package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface MainMenuContract {

    sealed class Event {
        data class GetPersonaje(val id: Int) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val personaje: Personaje? = null,
        val error: String? = null
    )
}