package com.example.rickandmortykmp.data.mapper

import com.example.rickandmortykmp.data.model.Result
import com.example.rickandmortykmp.domain.model.CharacterViewEntity

fun Result.toDomain(): CharacterViewEntity =
    CharacterViewEntity(
        id = id,
        name = name,
        image = image,
        status = status,
        species = species,
        gender = gender,
        originName = origin.name,
        locationName = location.name
    )

