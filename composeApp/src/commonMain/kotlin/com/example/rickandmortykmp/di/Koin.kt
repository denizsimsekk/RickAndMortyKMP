package com.example.rickandmortykmp.di

import com.example.rickandmortykmp.data.remote.ApiService
import com.example.rickandmortykmp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortykmp.data.repository.UserRepository
import com.example.rickandmortykmp.domain.repository.CharacterRepository
import com.example.rickandmortykmp.domain.usecase.GetCharactersUseCase
import com.example.rickandmortykmp.presentation.UserViewModel
import com.example.rickandmortykmp.presentation.characters.CharactersViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

private var _koin: Koin? = null

private val networkModule = module {
    single { ApiService() }
}

private val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl() }
    single { UserRepository() }
}

private val useCaseModule = module {
    factory { GetCharactersUseCase() }
}

private val viewModelModule = module {
    factory { CharactersViewModel(get()) }
    factory { UserViewModel(get()) }
}

fun initKoin(extraModules: List<Module> = emptyList()): KoinApplication {
    val app = startKoin {
        modules(
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
            *extraModules.toTypedArray()
        )
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

