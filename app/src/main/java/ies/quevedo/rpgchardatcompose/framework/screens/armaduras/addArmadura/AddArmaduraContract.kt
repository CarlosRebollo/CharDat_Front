package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.addArmadura

import ies.quevedo.rpgchardatcompose.domain.Armadura

interface AddArmaduraContract {

    sealed class Event {
        data class AddArmadura(val armadura: Armadura) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val error: String? = null
    )
}