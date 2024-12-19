package com.example.ucproomdatabase.ui.viewmodel.suplier

import com.example.ucproomdatabase.data.entity.Suplier

data class HomeSplUiState(
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
)