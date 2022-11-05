package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "escudo",
    foreignKeys = [
        ForeignKey(
            entity = PersonajeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idPJ"]
        )
    ]
)
data class EscudoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var value: Int,
    var quality: Int,
    var attackHability: Int,
    var damage: Int,
    var parry: Int,
    var description: String,
    val idPJ: Int
)
