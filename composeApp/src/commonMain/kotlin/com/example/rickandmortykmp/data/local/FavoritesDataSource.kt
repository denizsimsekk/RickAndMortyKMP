package com.example.rickandmortykmp.data.local

import com.example.rickandmortykmp.db.AppDatabase
import com.example.rickandmortykmp.domain.model.CharacterViewEntity

class FavoritesDataSource(private val database: AppDatabase) {
    private val queries = database.appDatabaseQueries

    fun insertFavorite(character: CharacterViewEntity) {
        queries.insertCharacter(
            id = character.id.toLong(),
            name = character.name,
            image = character.image,
            status = character.status,
            species = character.species,
            gender = character.gender,
            originName = character.originName,
            locationName = character.locationName
        )
    }

    fun removeFavorite(characterId: Int) {
        queries.deleteCharacter(characterId.toLong())
    }

    fun isFavorite(characterId: Int): Boolean =
        queries.isFavorite(characterId.toLong()).executeAsOne()

    fun getAllFavorites(): List<CharacterViewEntity> =
        queries.selectAllFavorites().executeAsList().map { row ->
            CharacterViewEntity(
                id = row.id.toInt(),
                name = row.name,
                image = row.image,
                status = row.status,
                species = row.species,
                gender = row.gender,
                originName = row.originName,
                locationName = row.locationName,
                isFavorite = true
            )
        }
}
