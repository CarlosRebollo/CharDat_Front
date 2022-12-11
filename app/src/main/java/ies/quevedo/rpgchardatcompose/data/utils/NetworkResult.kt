package ies.quevedo.rpgchardatcompose.data.utils

import ies.quevedo.rpgchardatcompose.domain.ApiResponse

sealed class NetworkResult<T>(
    var data: T? = null,
    val message: String? = null,
    val apiError: ApiResponse? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data = data)

    class ApiError<T>(
        data: T? = null,
        message: String? = null,
        apiError: ApiResponse?
    ) :
        NetworkResult<T>(data = data, message = message, apiError = apiError)

    class Error<T>(
        data: T? = null,
        message: String?,
        apiError: ApiResponse? = null
    ) :
        NetworkResult<T>(data = data, message = message, apiError = apiError)

    class Loading<T> : NetworkResult<T>()
}