package ies.quevedo.rpgchardatcompose.framework.screens.objetos.addObjeto

import ies.quevedo.rpgchardatcompose.domain.Objeto

interface AddObjetoContract {

    sealed class Event {
        data class AddObjeto(val objeto: Objeto) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val error: String? = null
    )
}