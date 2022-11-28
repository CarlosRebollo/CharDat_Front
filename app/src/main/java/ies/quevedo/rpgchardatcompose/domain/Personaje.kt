package ies.quevedo.rpgchardatcompose.domain

import java.util.*

data class Personaje(
    val id: Int = 0,
    var name: String = "",
    var clase: String = "",
    var description: String = "",
    var level: Int = 0,
    var totalHp: Int = 0,
    var totalStamina: Int = 0,
    var agility: Int = 0,
    var constitution: Int = 0,
    var dexterity: Int = 0,
    var strength: Int = 0,
    var intelligence: Int = 0,
    var perception: Int = 0,
    var power: Int = 0,
    var will: Int = 0,
    var creationDate: String = "",
    var armas: List<Arma>? = Collections.emptyList(),
    var armaduras: List<Armadura>? = Collections.emptyList(),
    var escudos: List<Escudo>? = Collections.emptyList(),
    var objetos: List<Objeto>? = Collections.emptyList()
)