package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang

data class DetailBrgUiState(
    val detailBrgUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
)

fun Barang.toDetailUiEvent () : BarangEvent{
    return BarangEvent(
        idbarang = idbarang,
        namabarang = namabarang,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namasuplier = namasuplier
    )
}