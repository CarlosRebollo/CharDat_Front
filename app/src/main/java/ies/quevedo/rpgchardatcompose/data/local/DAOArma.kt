package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.ArmaEntity

@Dao
interface DAOArma {

    @Query("SELECT * FROM arma WHERE id = :id")
    suspend fun getArma(id: Int): ArmaEntity

    @Query("SELECT * FROM arma WHERE idPJ = :idPJ")
    suspend fun getArmas(idPJ: Int): List<ArmaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArma(arma: ArmaEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllArmas(listaArmas: List<ArmaEntity>)

    @Update
    suspend fun updateArma(arma: ArmaEntity)

    @Delete
    suspend fun deleteArma(arma: ArmaEntity)

    @Query("DELETE FROM arma WHERE idPJ = :idPJ")
    suspend fun deleteAllArmasDelPersonaje(idPJ: Int)

    @Query("DELETE FROM arma")
    suspend fun deleteAllArmas()
}