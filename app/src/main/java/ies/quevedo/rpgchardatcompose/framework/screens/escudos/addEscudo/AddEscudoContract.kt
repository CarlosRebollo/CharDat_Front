package ies.quevedo.rpgchardatcompose.framework.screens.escudos.addEscudo

import ies.quevedo.rpgchardatcompose.domain.Escudo

interface AddEscudoContract {

    sealed class Event {
        data class AddEscudo(val escudo: Escudo) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val error: String? = null
    )
}