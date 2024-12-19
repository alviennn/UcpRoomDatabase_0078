package com.example.ucproomdatabase.ui.viewmodel.suplier

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase.TokoApp

object PenyediaSplViewModel {

    val Factory = viewModelFactory {
        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            HomeSplViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            DetailSplViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositorySpl
            )
        }
    }
}

fun CreationExtras.TokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)