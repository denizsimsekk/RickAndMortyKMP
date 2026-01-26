package com.example.rickandmortykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform