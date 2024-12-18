package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang

fun Barang.toDetailUiEvent () : BarangEvent{
    return BarangEvent(
        idbarang = idbarang,
        namabarang = namabarang,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
    )
}