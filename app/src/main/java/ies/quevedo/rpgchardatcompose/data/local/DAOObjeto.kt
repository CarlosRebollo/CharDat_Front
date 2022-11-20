package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.ObjetoEntity

@Dao
interface DAOObjeto {

    @Query("SELECT * FROM objeto WHERE id = :id")
    suspend fun getObjeto(id: Int): ObjetoEntity

    @Query("SELECT * FROM objeto WHERE idPJ = :idPJ")
    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObjeto(objeto: ObjetoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllObjetos(listaObjetos: List<ObjetoEntity>)

    @Update
    suspend fun updateObjeto(objeto: ObjetoEntity)

    @Delete
    suspend fun deleteObjeto(objeto: ObjetoEntity)

    @Delete
    suspend fun deleteAllObjetos(listaObjetos: List<ObjetoEntity>)
}