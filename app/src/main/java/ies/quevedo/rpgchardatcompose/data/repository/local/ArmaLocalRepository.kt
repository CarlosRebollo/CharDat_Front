package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toArma
import ies.quevedo.rpgchardatcompose.data.entities.toArmaEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOArma
import ies.quevedo.rpgchardatcompose.domain.Arma
import javax.inject.Inject

class ArmaLocalRepository @Inject constructor(private val daoArma: DAOArma) {

    suspend fun getArma(id: Int): Arma = daoArma.getArma(id = id).toArma()

    suspend fun getArmas(idPJ: Int): List<Arma> = daoArma.getArmas(idPJ = idPJ).map { it.toArma() }

    suspend fun insertArma(arma: Arma) = daoArma.insertArma(arma = arma.toArmaEntity())

    suspend fun updateArma(arma: Arma) = daoArma.updateArma(arma = arma.toArmaEntity())

    suspend fun deleteArma(arma: Arma) = daoArma.deleteArma(arma = arma.toArmaEntity())
}