package com.example.rickandmortykmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortykmp.data.remote.ApiService
import com.example.rickandmortykmp.data.repository.UserRepository
import com.example.rickandmortykmp.presentation.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val repository = UserRepository(ApiService())
            val viewModel = viewModel { UserViewModel(repository) }
            App(viewModel)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}