package ies.quevedo.rpgchardatcompose.domain

data class Usuario(
    val id: String,
    var nombre: String = "",
    val email: String = "",
    val password: String = "",
    val rol: String = "",
    var estado: Boolean = false
)
