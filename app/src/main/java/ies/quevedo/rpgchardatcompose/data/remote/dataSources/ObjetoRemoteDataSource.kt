package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Objeto
import ies.quevedo.rpgchardatcompose.network.ObjetoService
import javax.inject.Inject

class ObjetoRemoteDataSource @Inject constructor(
    private val objetoService: ObjetoService
) : BaseApiResponse() {

    suspend fun fetchObjeto(idObjeto: Int): NetworkResult<Objeto> {
        return safeApiCall(
            apiCall = { objetoService.getObjetoByID(idObjeto) }
        )
    }

    suspend fun fetchObjetos(idPJ: Int): NetworkResult<List<Objeto>> {
        return safeApiCall(
            apiCall = { objetoService.getObjetos(idPJ) }
        )
    }

    suspend fun postObjeto(objeto: Objeto): NetworkResult<Objeto> {
        return safeApiCall(
            apiCall = { objetoService.postObjeto(objeto) }
        )
    }

    suspend fun putObjeto(objeto: Objeto): NetworkResult<Objeto> {
        return safeApiCall(
            apiCall = { objetoService.putObjeto(objeto) }
        )
    }

    suspend fun deleteObjeto(idObjeto: Int): NetworkResult<Objeto> {
        return safeApiCall(
            apiCall = { objetoService.deleteObjeto(idObjeto) }
        )
    }
}