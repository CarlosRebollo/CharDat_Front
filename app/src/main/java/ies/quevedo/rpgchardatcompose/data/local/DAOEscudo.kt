package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.EscudoEntity

@Dao
interface DAOEscudo {

    @Query("SELECT * FROM escudo WHERE id = :id")
    fun getEscudo(id: Int): EscudoEntity

    @Query("SELECT * FROM escudo WHERE idPJ = :idPJ")
    fun getEscudos(idPJ: Int): List<EscudoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEscudo(escudo: EscudoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(escudos: List<EscudoEntity>)

    @Update
    fun updateEscudo(escudo: EscudoEntity)

    @Delete
    fun deleteEscudo(escudo: EscudoEntity)

    @Delete
    fun deleteAll(escudos: List<EscudoEntity>)
}