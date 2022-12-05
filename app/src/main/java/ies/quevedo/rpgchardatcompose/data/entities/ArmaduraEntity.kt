package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "armadura")
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