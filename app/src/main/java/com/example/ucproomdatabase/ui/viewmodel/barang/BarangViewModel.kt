package com.example.ucproomdatabase.ui.viewmodel.barang

import com.example.ucproomdatabase.data.entity.Barang

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

data class FormErrorState(
    val idbarang: String? = null,
    val namabarang: String? = null,
    val deskripsi: String? = null,
    val harga: Double? = null,
    val stok: Int? = null,
    val namasuplier: String? = null
){
    fun isValid(): Boolean{
        return idbarang != null && namabarang != null && deskripsi != null &&
                harga != null && stok != null && namasuplier != null
    }
}

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