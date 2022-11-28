package ies.quevedo.rpgchardatcompose.domain

data class Objeto(
    val id: Int = 0,
    var name: String = "",
    var description: String = "",
    var value: Int = 0,
    var amount: Int = 0,
    var idPJ: Int = 0
)