package com.example.rickandmortykmp.data.remote

import com.example.rickandmortykmp.data.model.UserResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService {
    private val client = ApiClient.httpClient
    private val baseUrl = "https://rickandmortyapi.com/api/character"

    suspend fun getCharacters(): UserResponse {
        return client.get(baseUrl).body()
    }

}