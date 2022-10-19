package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Arma
import ies.quevedo.rpgchardatcompose.network.ArmaService
import javax.inject.Inject

class ArmaRemoteDataSource @Inject constructor(
    private val armaService: ArmaService
) : BaseApiResponse() {

    suspend fun fetchArma(idArma: Int): NetworkResult<Arma> {
        return safeApiCall(
            apiCall = { armaService.getArmaByID(idArma) }
        )
    }

    suspend fun fetchArmas(idPJ: Int): NetworkResult<List<Arma>> {
        return safeApiCall(
            apiCall = { armaService.getArmas(idPJ) }
        )
    }

    suspend fun postArma(arma: Arma): NetworkResult<Arma> {
        return safeApiCall(
            apiCall = { armaService.postArma(arma) }
        )
    }

    suspend fun putArma(arma: Arma): NetworkResult<Arma> {
        return safeApiCall(
            apiCall = { armaService.putArma(arma) }
        )
    }

    suspend fun deleteArma(idArma: Int): NetworkResult<Arma> {
        return safeApiCall(
            apiCall = { armaService.deleteArma(idArma) }
        )
    }
}