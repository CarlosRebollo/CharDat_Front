package ies.quevedo.rpgchardatcompose.framework.screens.armas.addArma

import ies.quevedo.rpgchardatcompose.domain.Arma

interface AddArmaContract {

    sealed class Event {
        data class AddArma(val arma: Arma) : Event()
        data class ShowError(val error: String) : AddArmaContract.Event()
        object ErrorConsumed : AddArmaContract.Event()
    }

    data class State(
        val error: String? = null
    )
}