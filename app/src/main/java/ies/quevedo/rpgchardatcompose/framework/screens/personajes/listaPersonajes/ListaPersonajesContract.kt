package ies.quevedo.rpgchardatcompose.framework.screens.personajes.listaPersonajes

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.domain.Personaje

interface ListaPersonajesContract {

    sealed class Event {
        object GetAllPersonajes : Event()
        object DeleteAllRoom : Event()
        data class InsertAllRoom(val listaPersonajesDescargados: List<Personaje>?) : Event()
        data class GetPersonajeById(val idPersonaje: Int) : Event()
        data class DeletePersonaje(val personaje: Personaje) : Event()
        data class UploadPersonajes(val token: String, val personajes: List<Personaje>) : Event()
        data class DownloadPersonajes(val token: String) : Event()
        object GetTokenLocal : Event()
        object BorrarTokenLocal : Event()
        data class ShowError(val error: String) : Event()
        object ShowDialog : Event()
        object DismissDialog : Event()
        object ErrorConsumed : Event()
        object RespuestaExitosaConsumed : Event()
        object GetAllPersonajesConObjetos : Event()
    }

    data class State(
        var respuestaExitosaUpload: Boolean = false,
        var respuestaExitosaDownload: Boolean = false,
        var usuarioLogueado: UsuarioEntity? = null,
        var personaje: Personaje? = null,
        var listaPersonajes: List<Personaje>? = null,
        var listaPersonajesDescargados: List<Personaje>? = null,
        var listaPersonajesCompletos: List<Personaje>? = null,
        var showDialog: Boolean = false,
        val isLoading: Boolean = false,
        var error: String? = null
    )
}