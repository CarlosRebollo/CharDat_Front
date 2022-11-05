package ies.quevedo.rpgchardatcompose.data.local

import androidx.room.*
import ies.quevedo.rpgchardatcompose.data.entities.ArmaduraEntity

@Dao
interface DAOArmadura {

    @Query("SELECT * FROM armadura WHERE id = :id")
    suspend fun getArmadura(id: Int): ArmaduraEntity

    @Query("SELECT * FROM armadura WHERE idPJ = :idPJ")
    suspend fun getArmaduras(idPJ: Int): List<ArmaduraEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArmadura(armadura: ArmaduraEntity)

    @Update
    suspend fun updateArmadura(armadura: ArmaduraEntity)

    @Delete
    suspend fun deleteArmadura(armadura: ArmaduraEntity)
}