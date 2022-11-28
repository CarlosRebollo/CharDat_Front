package ies.quevedo.rpgchardatcompose.framework.screens.armaduras.listaArmaduras

import ies.quevedo.rpgchardatcompose.domain.Armadura

interface ListaArmadurasContract {

    sealed class Event {
        data class GetAllArmaduras(val idPersonaje: Int) : Event()
        data class DeleteArmadura(val armadura: Armadura) : Event()
        data class ShowError(val error: String?) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        var armaduraBorrada: Armadura? = null,
        var listaArmaduras: List<Armadura>? = emptyList(),
        var error: String? = null
    )
}