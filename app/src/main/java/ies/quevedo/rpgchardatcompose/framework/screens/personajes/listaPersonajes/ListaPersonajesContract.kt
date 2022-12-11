package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ListaPersonajesContract {

    sealed class Event {
        data class DeletePersonaje(val personaje: Personaje) : Event()
        data class DownloadPersonajes(val token: String) : Event()
        data class GetPersonajeById(val idPersonaje: Int) : Event()
        data class InsertAllRoom(val listaPersonajesDescargados: List<Personaje>?) : Event()
        data class UploadPersonajes(val token: String, val personajes: List<Personaje>) : Event()
        data class ShowError(val error: String) : Event()
        object BorrarTokenLocal : Event()
        object DeleteAllRoom : Event()
        object DismissDialog : Event()
        object ErrorConsumed : Event()
        object GetAllPersonajesConObjetos : Event()
        object GetTokenLocal : Event()
        object RespuestaExitosaConsumed : Event()
        object ShowDialog : Event()
    }

    data class State(
        var respuestaExitosaUpload: Boolean = false,
        var respuestaExitosaDownload: Boolean = false,
        var usuarioLogueado: UsuarioEntity? = null,
        var personaje: Personaje? = null,
        var listaPersonajesDescargados: List<Personaje>? = emptyList(),
        var listaPersonajesCompletos: List<Personaje>? = emptyList(),
        var showDialog: Boolean = false,
        val isLoading: Boolean = false,
        var error: String? = null,
    )
}