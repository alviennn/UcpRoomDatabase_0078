package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idbarang = idbarang,
    namabarang = namabarang,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namasuplier = namasuplier
)