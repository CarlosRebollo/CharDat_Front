package ies.quevedo.rpgchardatcompose.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = false)
    val idUsuario: String,
    var name: String,
    var JWT: String
)