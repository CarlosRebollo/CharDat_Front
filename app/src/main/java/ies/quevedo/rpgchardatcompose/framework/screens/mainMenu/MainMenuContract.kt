package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import ies.quevedo.rpgchardatcompose.domain.Personaje

interface MainMenuContract {

    sealed class Event {
        data class FetchPersonaje(val id: Int) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val personaje: Personaje? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}