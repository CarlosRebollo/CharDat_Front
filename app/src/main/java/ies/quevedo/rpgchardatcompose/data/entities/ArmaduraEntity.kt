package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "armadura",
    foreignKeys = [
        ForeignKey(
            entity = PersonajeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idPJ"]
        )
    ]
)
data class ArmaduraEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var value: Int,
    var quality: Int,
    var armor: Int,
    var ta: Int,
    var description: String,
    val idPJ: Int
)