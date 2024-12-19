package com.example.ucproomdatabase.ui.viewmodel.suplier

import com.example.ucproomdatabase.data.entity.Suplier

fun Suplier.toDetailUiEvent () : SuplierEvent{
    return SuplierEvent(
        idsuplier = idsuplier,
        namasuplier = namasuplier,
        kontak = kontak,
        alamat = alamat
    )
}