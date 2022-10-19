package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import javax.inject.Inject

class UsuarioRemoteDataSource @Inject constructor(
    private val usuarioService: UsuarioService
): BaseApiResponse() {


}