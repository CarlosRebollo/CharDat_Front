package ies.quevedo.rpgchardatcompose.domain

data class Usuario(
    val nombre: String = "",
    val email: String = "",
    val password: String = "",
    val rol: String = "",
    val estado: Boolean = false
)
