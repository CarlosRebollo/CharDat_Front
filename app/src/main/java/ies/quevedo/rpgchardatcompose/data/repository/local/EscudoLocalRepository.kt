package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.toEscudo
import ies.quevedo.rpgchardatcompose.data.entities.toEscudoEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOEscudo
import ies.quevedo.rpgchardatcompose.domain.Escudo
import javax.inject.Inject

class EscudoLocalRepository @Inject constructor(private val daoEscudo: DAOEscudo) {

    suspend fun getEscudo(idEscudo: Int): Escudo = daoEscudo.getEscudo(id = idEscudo).toEscudo()

    suspend fun getEscudos(idPJ: Int): List<Escudo> =
        daoEscudo.getEscudos(idPJ = idPJ).map { it.toEscudo() }

    suspend fun insertEscudo(escudo: Escudo) =
        daoEscudo.insertEscudo(escudo = escudo.toEscudoEntity())

    suspend fun insertAllEscudos(listaEscudos: List<Escudo>) =
        daoEscudo.insertAllEscudos(listaEscudos = listaEscudos.map { it.toEscudoEntity() })

    suspend fun updateEscudo(escudo: Escudo) =
        daoEscudo.updateEscudo(escudo = escudo.toEscudoEntity())

    suspend fun deleteEscudo(escudo: Escudo) =
        daoEscudo.deleteEscudo(escudo = escudo.toEscudoEntity())

    suspend fun deleteAllEscudos(listaEscudos: List<Escudo>) =
        daoEscudo.deleteAllEscudos(listaEscudos = listaEscudos.map { it.toEscudoEntity() })
}