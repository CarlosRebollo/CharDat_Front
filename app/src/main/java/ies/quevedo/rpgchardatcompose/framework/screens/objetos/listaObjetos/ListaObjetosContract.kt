package ies.quevedo.rpgchardatcompose.framework.screens.objetos.listaObjetos

import ies.quevedo.rpgchardatcompose.domain.Objeto

interface ListaObjetosContract {

    sealed class Event {
        data class GetAllObjetos(val idPersonaje: Int) : Event()
        data class DeleteObjeto(val objeto: Objeto) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var objetoBorrado: Objeto? = null,
        var listaObjetos: List<Objeto>? = emptyList(),
        var error: String? = null
    )
}