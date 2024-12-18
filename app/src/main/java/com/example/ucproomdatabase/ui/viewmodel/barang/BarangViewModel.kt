package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang



data class BarangEvent(
    val idbarang: String = "",
    val namabarang: String = "",
    val deskripsi: String = "",
    val harga: Double = 0.0,
    val stok: Int = 0,
    val namasuplier: String = ""
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idbarang = idbarang,
    namabarang = namabarang,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namasuplier = namasuplier
)