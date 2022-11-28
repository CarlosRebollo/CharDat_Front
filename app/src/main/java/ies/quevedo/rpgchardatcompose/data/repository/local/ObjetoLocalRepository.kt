package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toObjeto
import ies.quevedo.rpgchardatcompose.data.entities.toObjetoEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOObjeto
import ies.quevedo.rpgchardatcompose.domain.Objeto
import javax.inject.Inject

class ObjetoLocalRepository @Inject constructor(private val daoObjeto: DAOObjeto) {

    suspend fun getObjeto(idObjeto: Int): Objeto = daoObjeto.getObjeto(id = idObjeto).toObjeto()

    suspend fun getObjetos(idPJ: Int): List<Objeto> =
        daoObjeto.getObjetos(idPJ = idPJ).map { it.toObjeto() }

    suspend fun insertObjeto(objeto: Objeto) =
        daoObjeto.insertObjeto(objeto = objeto.toObjetoEntity())

    suspend fun insertAllObjetos(listaObjetos: List<Objeto>) =
        daoObjeto.insertAllObjetos(listaObjetos = listaObjetos.map { it.toObjetoEntity() })

    suspend fun updateObjeto(objeto: Objeto) =
        daoObjeto.updateObjeto(objeto = objeto.toObjetoEntity())

    suspend fun deleteObjeto(objeto: Objeto) =
        daoObjeto.deleteObjeto(objeto = objeto.toObjetoEntity())

    suspend fun deleteAllObjetos(listaObjetos: List<Objeto>) =
        daoObjeto.deleteAllObjetos(listaObjetos = listaObjetos.map { it.toObjetoEntity() })
}