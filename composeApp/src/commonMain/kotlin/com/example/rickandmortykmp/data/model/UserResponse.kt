package com.example.rickandmortykmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val info: Info,
    val results: List<Result>
)