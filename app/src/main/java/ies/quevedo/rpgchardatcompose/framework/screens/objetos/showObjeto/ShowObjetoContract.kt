package ies.quevedo.rpgchardatcompose.framework.screens.objetos.showObjeto

import ies.quevedo.rpgchardatcompose.domain.Objeto

interface ShowObjetoContract {

    sealed class Event {
        data class GetObjeto(val idObjeto: Int) : Event()
        data class UpdateObjeto(val objeto: Objeto) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val objeto: Objeto? = null,
        val error: String? = null
    )
}