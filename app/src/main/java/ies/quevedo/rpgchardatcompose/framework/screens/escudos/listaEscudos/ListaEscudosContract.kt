package ies.quevedo.rpgchardatcompose.framework.screens.escudos.listaEscudos

import ies.quevedo.rpgchardatcompose.domain.Escudo

interface ListaEscudosContract {

    sealed class Event {
        data class GetAllEscudos(val idPersonaje: Int) : Event()
        data class DeleteEscudo(val escudo: Escudo) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var escudoBorrado: Escudo? = null,
        var listaEscudos: List<Escudo>? = emptyList(),
        var error: String? = null
    )
}