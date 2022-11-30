package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.login

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.domain.Usuario

interface LoginUsuarioContract {

    sealed class Event {
        data class LoginUsuario(val usuario: Usuario) : Event()
        data class InsertUsuarioToken(val usuario: UsuarioEntity?) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val usuarioLogueado: UsuarioEntity? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}