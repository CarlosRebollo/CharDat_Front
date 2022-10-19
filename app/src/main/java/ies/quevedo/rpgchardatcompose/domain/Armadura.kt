package ies.quevedo.rpgchardatcompose.domain

data class Armadura(
    val id: Int = 0,
    var name: String,
    var value: Int,
    var weight: Double,
    var quality: Int,
    var armor: Int,
    var fil: Int,
    var con: Int,
    var pen: Int,
    var cal: Int,
    var ele: Int,
    var fri: Int,
    var ene: Int,
    var description: String,
    val idPJ: Int
)