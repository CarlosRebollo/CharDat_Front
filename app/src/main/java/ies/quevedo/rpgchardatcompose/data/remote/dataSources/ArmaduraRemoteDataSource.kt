package ies.quevedo.rpgchardatcompose.data.remote.dataSources

import ies.quevedo.rpgchardatcompose.data.remote.BaseApiResponse
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.Armadura
import ies.quevedo.rpgchardatcompose.network.ArmaduraService
import javax.inject.Inject

class ArmaduraRemoteDataSource @Inject constructor(
    private val armaduraService: ArmaduraService
) : BaseApiResponse() {

    suspend fun fetchArmadura(idArmadura: Int): NetworkResult<Armadura> {
        return safeApiCall(
            apiCall = { armaduraService.getArmaduraByID(idArmadura) },
        )
    }

    suspend fun fetchArmaduras(idPJ: Int): NetworkResult<List<Armadura>> {
        return safeApiCall(
            apiCall = { armaduraService.getArmaduras(idPJ) },
        )
    }

    suspend fun postArmadura(armadura: Armadura): NetworkResult<Armadura> {
        return safeApiCall(
            apiCall = { armaduraService.postArmadura(armadura) },
        )
    }

    suspend fun putArmadura(armadura: Armadura): NetworkResult<Armadura> {
        return safeApiCall(
            apiCall = { armaduraService.putArmadura(armadura) },
        )
    }

    suspend fun deleteArmadura(idArmadura: Int): NetworkResult<Armadura> {
        return safeApiCall(
            apiCall = { armaduraService.deleteArmadura(idArmadura) },
        )
    }
}