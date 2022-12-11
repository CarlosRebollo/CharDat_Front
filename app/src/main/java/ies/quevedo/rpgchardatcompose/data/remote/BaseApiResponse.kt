package ies.quevedo.rpgchardatcompose.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ies.quevedo.rpgchardatcompose.data.utils.NetworkResult
import ies.quevedo.rpgchardatcompose.domain.ApiResponse
import retrofit2.Response


abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(data = body)
                }
            }
            val gson = Gson()
            val type = object : TypeToken<ApiResponse>() {}.type
            val errorResponse: ApiResponse? =
                gson.fromJson(response.errorBody()!!.charStream(), type)
            return NetworkResult.ApiError(apiError = errorResponse)
        } catch (e: Exception) {
            return NetworkResult.Error(message = e.message)
        }
    }
}