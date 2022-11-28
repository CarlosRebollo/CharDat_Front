package ies.quevedo.rpgchardatcompose.domain

data class Armadura(
    val id: Int = 0,
    var name: String = "",
    var description: String = "",
    var armor: Int = 0,
    var ta: Int = 0,
    var value: Int = 0,
    var quality: Int = 0,
    var idPJ: Int = 0
)