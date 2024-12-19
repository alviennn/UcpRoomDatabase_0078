package com.example.ucproomdatabase.ui.viewmodel.suplier

import com.example.ucproomdatabase.data.entity.Suplier



data class DetailSupUiState(
    val detailSupUiEvent: SuplierEvent = SuplierEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty : Boolean
        get() = detailSupUiEvent == SuplierEvent()

    val isUiEventNotEmpty : Boolean
        get() = detailSupUiEvent != SuplierEvent()
}

fun Suplier.toDetailUiEvent () : SuplierEvent{
    return SuplierEvent(
        idsuplier = idsuplier,
        namasuplier = namasuplier,
        kontak = kontak,
        alamat = alamat
    )
}