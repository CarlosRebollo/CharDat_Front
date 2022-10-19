package ies.quevedo.rpgchardatcompose.domain

data class Arma(
    val id: Int,
    var name: String,
    var value: Int,
    var weight: Double,
    var quality: Int,
    var turn: Int,
    var attackHability: Int,
    var damage: Int,
    var parry: Int,
    var description: String,
    val idPJ: Int
)
