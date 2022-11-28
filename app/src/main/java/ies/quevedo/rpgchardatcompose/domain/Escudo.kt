package ies.quevedo.rpgchardatcompose.domain

data class Escudo(
    val id: Int = 0,
    var name: String = "",
    var description: String = "",
    var attackHability: Int = 0,
    var damage: Int = 0,
    var parry: Int = 0,
    var value: Int = 0,
    var quality: Int = 0,
    var idPJ: Int = 0
)