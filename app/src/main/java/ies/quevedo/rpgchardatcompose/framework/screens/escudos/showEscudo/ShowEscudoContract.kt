package ies.quevedo.rpgchardatcompose.framework.screens.escudos.showEscudo

import ies.quevedo.rpgchardatcompose.domain.Escudo

interface ShowEscudoContract {

    sealed class Event {
        data class GetEscudo(val idEscudo: Int) : Event()
        data class UpdateEscudo(val escudo: Escudo) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val escudo: Escudo? = null,
        val error: String? = null
    )
}