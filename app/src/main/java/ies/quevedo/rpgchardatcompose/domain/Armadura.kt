package ies.quevedo.rpgchardatcompose.domain

data class Armadura(
    val id: Int = 0,
    var name: String,
    var value: Int,
    var quality: Int,
    var armor: Int,
    var ta: Int,
    var description: String,
    val idPJ: Int
)