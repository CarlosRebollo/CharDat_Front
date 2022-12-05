package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "objeto")
data class ObjetoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var value: Int,
    var amount: Int,
    var description: String,
    val idPJ: Int
)