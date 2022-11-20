package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personaje")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var clase: String,
    var level: Int = 0,
    var description: String,
    var totalHP: Int = 0,
    var totalStamina: Int = 0,
    var agility: Int = 0,
    var constitution: Int = 0,
    var dexterity: Int = 0,
    var strength: Int = 0,
    var intelligence: Int = 0,
    var perception: Int = 0,
    var power: Int = 0,
    var will: Int = 0,
    var creationDate: String,
)