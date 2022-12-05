package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOUsuario
import javax.inject.Inject

class UsuarioLocalRepository @Inject constructor(private val daoUsuario: DAOUsuario) {

    suspend fun insertUsuarioConToken(usuario: UsuarioEntity?) =
        usuario?.let { daoUsuario.insertToken(usuario = it) }

    suspend fun getTokenLocal(): UsuarioEntity = daoUsuario.getTokenLocal()

    suspend fun borrarTokenLocal() = daoUsuario.borrarTokenLocal()
}