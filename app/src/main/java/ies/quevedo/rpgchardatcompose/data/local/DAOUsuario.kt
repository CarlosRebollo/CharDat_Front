package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity

@Dao
interface DAOUsuario {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToken(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario")
    suspend fun getTokenLocal(): UsuarioEntity

    @Query("DELETE FROM usuario")
    suspend fun borrarTokenLocal()
}