package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang

fun Barang.toUIStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailUiEvent(),
)