package ies.quevedo.rpgchardatcompose.framework.screens.armas.showArma

import ies.quevedo.rpgchardatcompose.domain.Arma

interface ShowArmaContract {

    sealed class Event {
        data class GetArma(val idArma: Int) : Event()
        data class UpdateArma(val arma: Arma) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val arma: Arma? = null,
        val error: String? = null
    )
}