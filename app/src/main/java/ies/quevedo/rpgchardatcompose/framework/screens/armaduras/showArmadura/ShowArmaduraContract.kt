package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.showArmadura

import ies.quevedo.rpgchardatcompose.domain.Armadura

interface ShowArmaduraContract {

    sealed class Event {
        data class GetArmadura(val idArmadura: Int) : Event()
        data class UpdateArma(val armadura: Armadura) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val arma: Armadura? = null,
        val error: String? = null
    )
}