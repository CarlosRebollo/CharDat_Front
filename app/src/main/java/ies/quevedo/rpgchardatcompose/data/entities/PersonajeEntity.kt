package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personaje")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var clase: String,
    var level: Int,
    var description: String,
    var totalHP: Int,
    var totalStamina: Int,
    var agility: Int,
    var constitution: Int,
    var dexterity: Int,
    var strength: Int,
    var intelligence: Int,
    var perception: Int,
    var power: Int,
    var will: Int,
    var creationDate: String,
)