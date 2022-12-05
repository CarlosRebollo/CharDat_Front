package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = false)
    var token: String = "noToken",
    var fechaValidezToken: String = ""
)