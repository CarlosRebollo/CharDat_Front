package ies.quevedo.rpgchardatcompose.domain

data class Objeto(
    val id: Int = 0,
    var name: String,
    var value: Int,
    var weight: Double,
    var amount: Int,
    var description: String,
    val idPJ: Int
)