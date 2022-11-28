package ies.quevedo.rpgchardatcompose.framework.screens.armas.listaArmas

import ies.quevedo.rpgchardatcompose.domain.Arma

interface ListaArmasContract {

    sealed class Event {
        data class GetAllArmas(val idPersonaje: Int) : Event()
        data class DeleteArma(val arma: Arma) : Event()
        data class ShowError(val error: String?) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var armaBorrada: Arma? = null,
        var listaArmas: List<Arma>? = emptyList(),
        var error: String? = null
    )
}