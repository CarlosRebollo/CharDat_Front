package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.EscudoEntity

@Dao
interface DAOEscudo {

    @Query("SELECT * FROM escudo WHERE id = :id")
    suspend fun getEscudo(id: Int): EscudoEntity

    @Query("SELECT * FROM escudo WHERE idPJ = :idPJ")
    suspend fun getEscudos(idPJ: Int): List<EscudoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEscudo(escudo: EscudoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllEscudos(listaEscudos: List<EscudoEntity>)

    @Update
    suspend fun updateEscudo(escudo: EscudoEntity)

    @Delete
    suspend fun deleteEscudo(escudo: EscudoEntity)

    @Delete
    suspend fun deleteAllEscudos(listaEscudos: List<EscudoEntity>)
}