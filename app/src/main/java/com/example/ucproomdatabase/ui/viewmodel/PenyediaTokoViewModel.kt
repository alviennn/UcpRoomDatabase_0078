package com.example.ucproomdatabase.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdatabase.TokoApp
import com.example.ucproomdatabase.ui.viewmodel.barang.BarangViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucproomdatabase.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucproomdatabase.ui.viewmodel.suplier.HomeSplViewModel
import com.example.ucproomdatabase.ui.viewmodel.suplier.SuplierViewModel

object PenyediaTokoViewModel{

    val Factory = viewModelFactory {
        initializer {
            HomeTokoViewModel(
                TokoApp().containerApp.repositoryBrg,
                TokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            HomeBrgViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            HomeSplViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            BarangViewModel(
                TokoApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }
    }
}

fun CreationExtras.TokoApp() : TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)