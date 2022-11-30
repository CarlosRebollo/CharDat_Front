package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity

@Dao
interface DAOUsuario {

    @Query("SELECT * FROM usuario WHERE correoElectronico = :correoElectronico")
    suspend fun getUsuarioByCorreoElectronico(correoElectronico: String): UsuarioEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsuarioConToken(usuario: UsuarioEntity)
}