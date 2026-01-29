package com.example.rickandmortykmp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object RouteCharacters : NavKey

@Serializable
data class RouteCharacterDetail(val characterId: Int) : NavKey

@Serializable
data object RouteFavorites : NavKey
