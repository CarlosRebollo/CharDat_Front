package ies.quevedo.rpgchardatcompose.framework.screens.usuarios.registro

import ies.quevedo.rpgchardatcompose.domain.Usuario

interface RegistroUsuarioContract {

    sealed class Event {
        data class RegisterUsuario(val usuario: Usuario) : Event()
        data class ShowError(val error: String) : Event()
        object ErrorConsumed : Event()
    }

    data class State(
        val error: String? = null
    )
}