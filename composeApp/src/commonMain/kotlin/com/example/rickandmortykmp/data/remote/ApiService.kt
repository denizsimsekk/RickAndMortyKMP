package com.example.rickandmortykmp.data.remote

import com.example.rickandmortykmp.data.model.Result
import com.example.rickandmortykmp.data.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {
    private val baseUrl = "https://rickandmortyapi.com/api/character"

    suspend fun getCharacters(): UserResponse =
        client.get(baseUrl).body()

    suspend fun getCharacter(id: Int): Result =
        client.get("$baseUrl/$id").body()
}