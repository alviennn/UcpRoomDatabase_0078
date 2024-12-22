package com.example.ucproomdatabase.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdatabase.ui.viewmodel.HomeTokoViewModel
import com.example.ucproomdatabase.ui.viewmodel.PenyediaTokoViewModel

object NamaSpl {
    @Composable
    fun options(
        supplierHomeViewModel: HomeTokoViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
    ): List<String> {
        val dataNama by supplierHomeViewModel.homeSplUiState.collectAsState()
        val namaSupplier = dataNama.listSuplier.map { it.namasuplier }
        return namaSupplier
    }
}