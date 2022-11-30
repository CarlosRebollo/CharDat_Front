package ies.quevedo.rpgchardatcompose.data.repository.local

import ies.quevedo.rpgchardatcompose.data.entities.UsuarioEntity
import ies.quevedo.rpgchardatcompose.data.local.DAOUsuario
import javax.inject.Inject

class UsuarioLocalRepository @Inject constructor(private val daoUsuario: DAOUsuario) {

    suspend fun getUsuarioByCorreoElectronico(correoElectronico: String): UsuarioEntity =
        daoUsuario.getUsuarioByCorreoElectronico(correoElectronico = correoElectronico)

    suspend fun insertUsuarioConToken(usuario: UsuarioEntity?) =
        usuario?.let { daoUsuario.insertUsuarioConToken(usuario = it) }
}