package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.domain.Usuario

interface RegistroUsuarioContract {

    sealed class Event {
        data class RegisterUsuario(val usuario: Usuario) : Event()
        data class LoginUsuario(val usuario: Usuario) : Event()
        data class InsertUsuarioToken(val usuario: UsuarioEntity?) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val usuarioLogueado: UsuarioEntity? = null,
        val usuarioRegistrado: Usuario? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}