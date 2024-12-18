package com.example.ucproomdatabase.ui.viewmodel.barang

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase.TokoApp

object PenyediaBrgViewModel{

    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeBrgViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            updateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }
    }
}

fun CreationExtras.TokoApp() : TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)