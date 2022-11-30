package ies.quevedo.rpgchardatcompose.data.entities

import ies.quevedo.rpgchardatcompose.domain.*
import java.util.*

fun PersonajeEntity.toPersonaje(): Personaje {
    return Personaje(
        id = this.id,
        name = this.name,
        clase = this.clase,
        level = this.level,
        description = this.description,
        totalHp = this.totalHP,
        totalStamina = this.totalStamina,
        agility = this.agility,
        constitution = this.constitution,
        dexterity = this.dexterity,
        strength = this.strength,
        intelligence = this.intelligence,
        perception = this.perception,
        power = this.power,
        will = this.will,
        creationDate = this.creationDate,
        armas = Collections.emptyList(),
        armaduras = Collections.emptyList(),
        escudos = Collections.emptyList(),
        objetos = Collections.emptyList()
    )
}

fun ArmaEntity.toArma(): Arma {
    return Arma(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        turn = this.turn,
        attackHability = this.attackHability,
        damage = this.damage,
        parry = this.parry,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun ArmaduraEntity.toArmadura(): Armadura {
    return Armadura(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        armor = this.armor,
        ta = this.ta,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun EscudoEntity.toEscudo(): Escudo {
    return Escudo(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        attackHability = this.attackHability,
        damage = this.damage,
        parry = this.parry,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun ObjetoEntity.toObjeto(): Objeto {
    return Objeto(
        id = this.id,
        name = this.name,
        value = this.value,
        amount = this.amount,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun Personaje.toPersonajeEntity(): PersonajeEntity {
    return PersonajeEntity(
        id = this.id,
        name = this.name,
        clase = this.clase,
        level = this.level,
        description = this.description,
        totalHP = this.totalHp,
        totalStamina = this.totalStamina,
        agility = this.agility,
        constitution = this.constitution,
        dexterity = this.dexterity,
        strength = this.strength,
        intelligence = this.intelligence,
        perception = this.perception,
        power = this.power,
        will = this.will,
        creationDate = this.creationDate
    )
}

fun Arma.toArmaEntity(): ArmaEntity {
    return ArmaEntity(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        turn = this.turn,
        attackHability = this.attackHability,
        damage = this.damage,
        parry = this.parry,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun Armadura.toArmaduraEntity(): ArmaduraEntity {
    return ArmaduraEntity(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        armor = this.armor,
        ta = this.ta,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun Escudo.toEscudoEntity(): EscudoEntity {
    return EscudoEntity(
        id = this.id,
        name = this.name,
        value = this.value,
        quality = this.quality,
        attackHability = this.attackHability,
        damage = this.damage,
        parry = this.parry,
        description = this.description,
        idPJ = this.idPJ
    )
}

fun Objeto.toObjetoEntity(): ObjetoEntity {
    return ObjetoEntity(
        id = this.id,
        name = this.name,
        value = this.value,
        amount = this.amount,
        description = this.description,
        idPJ = this.idPJ
    )
}