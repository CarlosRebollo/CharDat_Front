package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toArmadura
import ies.quevedo.rpgchardatcompose.data.entities.toArmaduraEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOArmadura
import ies.quevedo.rpgchardatcompose.domain.Armadura
import javax.inject.Inject

class ArmaduraLocalRepository @Inject constructor(private val daoArmadura: DAOArmadura) {

    suspend fun getArmadura(id: Int): Armadura = daoArmadura.getArmadura(id = id).toArmadura()

    suspend fun getArmaduras(idPJ: Int): List<Armadura> =
        daoArmadura.getArmaduras(idPJ = idPJ).map { it.toArmadura() }

    suspend fun insertArmadura(armadura: Armadura) =
        daoArmadura.insertArmadura(armadura = armadura.toArmaduraEntity())

    suspend fun updateArmadura(armadura: Armadura) =
        daoArmadura.updateArmadura(armadura = armadura.toArmaduraEntity())

    suspend fun deleteArmadura(armadura: Armadura) =
        daoArmadura.deleteArmadura(armadura = armadura.toArmaduraEntity())
}