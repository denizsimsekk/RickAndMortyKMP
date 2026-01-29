package com.example.rickandmortykmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.rickandmortykmp.presentation.characterdetail.CharacterDetailScreen
import com.example.rickandmortykmp.presentation.characterdetail.CharacterDetailViewModel
import com.example.rickandmortykmp.presentation.characters.CharactersScreen
import com.example.rickandmortykmp.presentation.characters.CharactersViewModel
import com.example.rickandmortykmp.presentation.favorites.FavoritesScreen
import com.example.rickandmortykmp.presentation.favorites.FavoritesViewModel
import com.example.rickandmortykmp.presentation.navigation.RouteCharacterDetail
import com.example.rickandmortykmp.presentation.navigation.RouteCharacters
import com.example.rickandmortykmp.presentation.navigation.RouteFavorites
import com.example.rickandmortykmp.di.koin
import org.koin.core.parameter.parametersOf

@Composable
fun App() {
    val backStack = remember { mutableStateListOf<NavKey>(RouteCharacters) }

    val rootKey = backStack.firstOrNull()
    val selectedTab = when (rootKey) {
        is RouteCharacters -> 0
        is RouteFavorites -> 1
        else -> 0
    }

    MaterialTheme {
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { backStack.clear(); backStack.add(RouteCharacters) },
                        icon = { Text("👥") },
                        label = { Text("Characters") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { backStack.clear(); backStack.add(RouteFavorites) },
                        icon = { Text("★") },
                        label = { Text("Favorites") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(paddingValues)
            ) {
                NavDisplay(
                    modifier = Modifier.fillMaxSize(),
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeAt(backStack.size - 1) },
                entryProvider = { key ->
                    when (key) {
                        is RouteCharacters -> NavEntry(key) {
                            val viewModel = remember { koin.get<CharactersViewModel>() }
                            CharactersScreen(
                                viewModel = viewModel,
                                onCharacterClick = { character ->
                                    backStack.add(RouteCharacterDetail(character.id))
                                }
                            )
                        }
                        is RouteCharacterDetail -> NavEntry(key) {
                            val viewModel = remember(key.characterId) {
                                koin.get<CharacterDetailViewModel> { parametersOf(key.characterId) }
                            }
                            CharacterDetailScreen(
                                viewModel = viewModel,
                                onBack = { if (backStack.size > 1) backStack.removeAt(backStack.size - 1) }
                            )
                        }
                        is RouteFavorites -> NavEntry(key) {
                            val viewModel = remember { koin.get<FavoritesViewModel>() }
                            FavoritesScreen(
                                viewModel = viewModel,
                                onCharacterClick = { character ->
                                    backStack.add(RouteCharacterDetail(character.id))
                                }
                            )
                        }
                        else -> NavEntry(key) {
                            Text("Unknown route")
                        }
                    }
                }
                )
            }
        }
    }
}
