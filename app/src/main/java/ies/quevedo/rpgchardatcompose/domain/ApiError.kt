package ies.quevedo.rpgchardatcompose.domain

data class ApiError(
    val value: String,
    val msg: String,
    val param: String,
    val location: String
)
