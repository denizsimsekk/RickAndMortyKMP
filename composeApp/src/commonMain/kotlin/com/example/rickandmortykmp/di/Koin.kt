package com.example.rickandmortykmp.di

import com.example.rickandmortykmp.data.local.DatabaseDriverFactory
import com.example.rickandmortykmp.data.local.FavoritesDataSource
import com.example.rickandmortykmp.db.AppDatabase
import com.example.rickandmortykmp.data.remote.ApiClient
import com.example.rickandmortykmp.data.remote.ApiService
import io.ktor.client.HttpClient
import com.example.rickandmortykmp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortykmp.data.repository.FavoritesRepositoryImpl
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import com.example.rickandmortykmp.domain.repository.FavoritesRepository
import com.example.rickandmortykmp.domain.usecase.AddFavoriteUseCase
import com.example.rickandmortykmp.domain.usecase.GetAllFavoritesUseCase
import com.example.rickandmortykmp.domain.usecase.GetCharacterByIdUseCase
import com.example.rickandmortykmp.domain.usecase.GetCharactersUseCase
import com.example.rickandmortykmp.domain.usecase.RemoveFavoriteUseCase
import com.example.rickandmortykmp.presentation.characterdetail.CharacterDetailViewModel
import com.example.rickandmortykmp.presentation.characters.CharactersViewModel
import com.example.rickandmortykmp.presentation.favorites.FavoritesViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

private var _koin: Koin? = null

private val networkModule = module {
    single { ApiClient.httpClient }
    single { ApiService(get()) }
}

private val databaseModule = module {
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
    single { FavoritesDataSource(get()) }
}

private val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
}

private val useCaseModule = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterByIdUseCase(get(), get()) }
    factory { GetAllFavoritesUseCase(get()) }
    factory { AddFavoriteUseCase(get()) }
    factory { RemoveFavoriteUseCase(get()) }
}

private val viewModelModule = module {
    factory { CharactersViewModel(get()) }
    factory { (characterId: Int) ->
        CharacterDetailViewModel(
            get(),
            get(),
            get(),
            characterId
        )
    }
    factory { FavoritesViewModel(get()) }
}

fun getAllModules(extraModules: List<Module> = emptyList()): List<Module> =
    listOf(networkModule, databaseModule, repositoryModule, useCaseModule, viewModelModule, getPlatformModule()) + extraModules

fun initKoin(extraModules: List<Module> = emptyList()): KoinApplication {
    val app = startKoin {
        applyPlatformConfig()
        modules(getAllModules(extraModules))
    }
    _koin = app.koin
    return app
}

/** Call before using [koin] (e.g. from iOS entry). Safe to call multiple times. */
fun ensureKoin(extraModules: List<Module> = emptyList()) {
    if (_koin == null) initKoin(extraModules)
}

val koin: Koin
    get() = _koin ?: error("Koin not initialized. Call initKoin() or ensureKoin() before using koin.")

