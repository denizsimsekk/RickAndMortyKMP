package com.example.rickandmortykmp.domain.model

data class CharacterViewEntity(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val isFavorite: Boolean? = null
)
